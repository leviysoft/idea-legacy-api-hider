package leviysoft.apihider

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionResult
import com.intellij.util.Consumer
import com.intellij.psi.PsiDocCommentOwner

class LegacyEliminator : CompletionContributor() {
    override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
        val consumer = Consumer<CompletionResult> { cr ->
            val lookupElement = cr.lookupElement
            val docCommentOwner = lookupElement?.psiElement?.navigationElement as? PsiDocCommentOwner
            val isDeprecated = docCommentOwner?.isDeprecated ?: false
            if (!isDeprecated) {
                result.addElement(cr.lookupElement)
            }
        }
        result.runRemainingContributors(parameters, consumer)
    }
}