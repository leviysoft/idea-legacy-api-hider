package leviysoft.rainbowpony

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionResult
import com.intellij.util.Consumer
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.xmlb.Constants
import com.intellij.psi.PsiDocCommentOwner

public class LegacyEliminator : CompletionContributor() {
    override fun fillCompletionVariants(parameters: CompletionParameters?, result: CompletionResultSet?) {
        val consumer = Consumer({(cr: CompletionResult) ->
            val lookupElement = cr.getLookupElement()
            val docCommentOwner = lookupElement?.getPsiElement()?.getNavigationElement() as? PsiDocCommentOwner
            val isDeprecated = docCommentOwner?.isDeprecated() ?: false
            if ( !isDeprecated ){
                result?.addElement(cr.getLookupElement())
            }
        })
        result?.runRemainingContributors(parameters!!, consumer)
    }


}