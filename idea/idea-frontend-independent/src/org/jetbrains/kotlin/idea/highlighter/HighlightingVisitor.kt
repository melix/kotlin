/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.highlighter

import com.intellij.lang.annotation.Annotation
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtNamedDeclaration
import org.jetbrains.kotlin.psi.KtVisitorVoid

abstract class HighlightingVisitor protected constructor(
    private val holder: AnnotationHolder
) : KtVisitorVoid() {

    protected fun createInfoAnnotation(element: PsiElement, message: String? = null, textAttributes: TextAttributesKey) {
        createInfoAnnotation(element.textRange, message, textAttributes)
    }

    protected fun createInfoAnnotation(element: PsiElement, message: String? = null) =
        createInfoAnnotation(element.textRange, message)

    protected open fun createInfoAnnotation(textRange: TextRange, message: String? = null, textAttributes: TextAttributesKey? = null) {
        (message?.let { holder.newAnnotation(HighlightSeverity.INFORMATION, it) }
            ?: holder.newSilentAnnotation(HighlightSeverity.INFORMATION))
            .range(textRange)
            .also { builder ->
                textAttributes?.let {
                    builder.textAttributes(it)
                }
            }
            .create()
    }

    protected fun highlightName(element: PsiElement, attributesKey: TextAttributesKey, message: String? = null) {
        if (NameHighlighter.namesHighlightingEnabled && !element.textRange.isEmpty) {
            createInfoAnnotation(element, message, attributesKey)
        }
    }

    protected fun highlightName(textRange: TextRange, attributesKey: TextAttributesKey, message: String? = null) {
        if (NameHighlighter.namesHighlightingEnabled) {
            createInfoAnnotation(textRange, message, attributesKey)
        }
    }

    protected fun highlightNamedDeclaration(declaration: KtNamedDeclaration, attributesKey: TextAttributesKey) {
        declaration.nameIdentifier?.let { highlightName(it, attributesKey) }
    }
}
