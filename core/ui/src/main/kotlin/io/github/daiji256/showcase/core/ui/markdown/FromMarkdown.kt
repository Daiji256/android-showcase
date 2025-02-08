package io.github.daiji256.showcase.core.ui.markdown

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import org.intellij.markdown.IElementType
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.MarkdownTokenTypes
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.ast.getTextInNode
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.parser.MarkdownParser

/**
 * Converts a Markdown formatted string into AnnotatedString.
 *
 * Note that Images are not **yet** available.
 *
 * @param markdownString Markdown formatted string to be parsed to construct [AnnotatedString]
 * @param style style applied to each Markdown elements
 */
@Stable
fun AnnotatedString.Companion.fromMarkdown(
    markdownString: String,
    style: LegacyMarkdownStyle = LegacyMarkdownStyle.Default,
): AnnotatedString =
    buildAnnotatedString {
        val flavour = CommonMarkFlavourDescriptor()
        val node = MarkdownParser(flavour).buildMarkdownTreeFromString(markdownString)
        val blocks = node.getLegacyBlocks(markdownString)
        blocks.forEachIndexed { index, block ->
            // Add spacing between paragraphs
            if (index != 0) withStyle(style = ParagraphStyle()) {}
            append(block = block, style = style)
        }
    }

@Immutable
data class LegacyMarkdownStyle(
    val emph: SpanStyle,
    val strong: SpanStyle,
    val code: SpanStyle,
    val link: TextLinkStyles,
    val h1: SpanStyle,
    val h2: SpanStyle,
    val h3: SpanStyle,
    val h4: SpanStyle,
    val h5: SpanStyle,
    val h6: SpanStyle,
    val codeLegacyBlock: SpanStyle,
) {
    companion object {
        val Default = LegacyMarkdownStyle(
            emph = SpanStyle(fontStyle = FontStyle.Italic),
            strong = SpanStyle(fontWeight = FontWeight.Bold),
            code = SpanStyle(fontFamily = FontFamily.Monospace),
            link = TextLinkStyles(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    color = Color(0xFF0001B4),
                ),
            ),
            h1 = SpanStyle(fontSize = 1.75.em),
            h2 = SpanStyle(fontSize = 1.5.em),
            h3 = SpanStyle(fontSize = 1.375.em),
            h4 = SpanStyle(fontSize = 1.25.em),
            h5 = SpanStyle(fontSize = 1.125.em),
            h6 = SpanStyle(fontSize = 1.0625.em),
            codeLegacyBlock = SpanStyle(fontFamily = FontFamily.Monospace, fontSize = 0.875.em),
        )
    }
}

private fun ASTNode.getLegacyBlocks(markdownString: String): List<LegacyBlock> =
    when (this.type) {
        MarkdownElementTypes.MARKDOWN_FILE ->
            children.flatMap { it.getLegacyBlocks(markdownString) }

        MarkdownElementTypes.PARAGRAPH,
        MarkdownElementTypes.UNORDERED_LIST,
        MarkdownElementTypes.ORDERED_LIST,
        MarkdownElementTypes.BLOCK_QUOTE,
        ->
            listOf(
                LegacyBlock.Paragraph(
                    contents = children.mapNotNull {
                        it.getLegacyContent(markdownString)
                    },
                ),
            )

        MarkdownElementTypes.SETEXT_1,
        MarkdownElementTypes.SETEXT_2,
        ->
            listOf(
                LegacyBlock.h(
                    type = this.type,
                    contents = children
                        .dropLast(2) // === or --- and EOL
                        .mapNotNull { it.getLegacyContent(markdownString) },
                ),
            )

        MarkdownElementTypes.ATX_1,
        MarkdownElementTypes.ATX_2,
        MarkdownElementTypes.ATX_3,
        MarkdownElementTypes.ATX_4,
        MarkdownElementTypes.ATX_5,
        MarkdownElementTypes.ATX_6,
        ->
            listOf(
                LegacyBlock.h(
                    type = this.type,
                    contents = children
                        .drop(1) // # or ## or ### or #### or ##### or ######
                        .mapNotNull { it.getLegacyContent(markdownString) },
                ),
            )

        MarkdownElementTypes.CODE_FENCE ->
            listOf(
                LegacyBlock.Code(
                    contents = children
                        .filterNot { it.type == MarkdownTokenTypes.CODE_FENCE_START }
                        .filterNot { it.type == MarkdownTokenTypes.CODE_FENCE_END }
                        .filterNot { it.type == MarkdownTokenTypes.FENCE_LANG }
                        .dropWhile { it.type == MarkdownTokenTypes.EOL }
                        .dropLastWhile { it.type == MarkdownTokenTypes.EOL }
                        .mapNotNull { it.getLegacyContent(markdownString) },
                ),
            )

        else -> emptyList()
    }

private fun ASTNode.getLegacyContent(markdownString: String): LegacyContent? =
    when (this.type) {
        MarkdownTokenTypes.FENCE_LANG ->
            null

        MarkdownElementTypes.EMPH ->
            LegacyContent.Emph(
                contents = children
                    .drop(1) // * or _
                    .dropLast(1) // * or _
                    .mapNotNull { it.getLegacyContent(markdownString) },
            )

        MarkdownElementTypes.STRONG ->
            LegacyContent.Strong(
                contents = children
                    .drop(2) // ** or __
                    .dropLast(2) // ** or __
                    .mapNotNull { it.getLegacyContent(markdownString) },
            )

        MarkdownElementTypes.CODE_SPAN ->
            LegacyContent.Code(
                contents = children
                    .drop(1) // `
                    .dropLast(1) // `
                    .mapNotNull { it.getLegacyContent(markdownString) },
            )

        MarkdownElementTypes.INLINE_LINK -> run {
            LegacyContent.Link(
                contents = children.find { it.type == MarkdownElementTypes.LINK_TEXT }?.children
                    ?.drop(1) // [
                    ?.dropLast(1) // ]
                    ?.mapNotNull { it.getLegacyContent(markdownString) }
                    ?: return@run null,
                url = children.findLast { it.type == MarkdownElementTypes.LINK_DESTINATION }
                    ?.getTextInNode(markdownString)?.toString()
                    ?: return@run null,
            )
        }

        MarkdownTokenTypes.ATX_CONTENT ->
            LegacyContent.Text(text = getTextInNode(markdownString).toString().trimStart())

        else ->
            LegacyContent.Text(text = getTextInNode(markdownString).toString())
    }

private sealed interface LegacyBlock {
    val contents: List<LegacyContent>

    data class Paragraph(override val contents: List<LegacyContent>) : LegacyBlock
    data class H1(override val contents: List<LegacyContent>) : LegacyBlock
    data class H2(override val contents: List<LegacyContent>) : LegacyBlock
    data class H3(override val contents: List<LegacyContent>) : LegacyBlock
    data class H4(override val contents: List<LegacyContent>) : LegacyBlock
    data class H5(override val contents: List<LegacyContent>) : LegacyBlock
    data class H6(override val contents: List<LegacyContent>) : LegacyBlock
    data class Code(override val contents: List<LegacyContent>) : LegacyBlock

    companion object {
        fun h(type: IElementType, contents: List<LegacyContent>): LegacyBlock =
            when (type) {
                MarkdownElementTypes.SETEXT_1 -> H1(contents = contents)
                MarkdownElementTypes.SETEXT_2 -> H2(contents = contents)
                MarkdownElementTypes.ATX_1 -> H1(contents = contents)
                MarkdownElementTypes.ATX_2 -> H2(contents = contents)
                MarkdownElementTypes.ATX_3 -> H3(contents = contents)
                MarkdownElementTypes.ATX_4 -> H4(contents = contents)
                MarkdownElementTypes.ATX_5 -> H5(contents = contents)
                MarkdownElementTypes.ATX_6 -> H6(contents = contents)
                else -> throw Error("Unknown type")
            }
    }
}

private sealed interface LegacyContent {
    data class Text(val text: String) : LegacyContent
    data class Emph(val contents: List<LegacyContent>) : LegacyContent
    data class Strong(val contents: List<LegacyContent>) : LegacyContent
    data class Code(val contents: List<LegacyContent>) : LegacyContent
    data class Link(val contents: List<LegacyContent>, val url: String) : LegacyContent
}

private fun AnnotatedString.Builder.append(block: LegacyBlock, style: LegacyMarkdownStyle) {
    withStyle(style = ParagraphStyle()) {
        withStyle(
            style = when (block) {
                is LegacyBlock.Paragraph -> SpanStyle()
                is LegacyBlock.H1 -> style.h1
                is LegacyBlock.H2 -> style.h2
                is LegacyBlock.H3 -> style.h3
                is LegacyBlock.H4 -> style.h4
                is LegacyBlock.H5 -> style.h5
                is LegacyBlock.H6 -> style.h6
                is LegacyBlock.Code -> style.codeLegacyBlock
            },
        ) {
            block.contents.forEach {
                append(content = it, style = style)
            }
        }
    }
}

private fun AnnotatedString.Builder.append(content: LegacyContent, style: LegacyMarkdownStyle) {
    when (content) {
        is LegacyContent.Text ->
            append(text = content.text)

        is LegacyContent.Emph ->
            withStyle(style = style.emph) {
                content.contents.forEach {
                    append(content = it, style = style)
                }
            }

        is LegacyContent.Strong ->
            withStyle(style = style.strong) {
                content.contents.forEach {
                    append(content = it, style = style)
                }
            }

        is LegacyContent.Code ->
            withStyle(style = style.code) {
                content.contents.forEach {
                    append(content = it, style = style)
                }
            }

        is LegacyContent.Link ->
            withLink(link = LinkAnnotation.Url(url = content.url, styles = style.link)) {
                content.contents.forEach {
                    append(content = it, style = style)
                }
            }
    }
}

@Preview
@Composable
private fun MarkdownPreview() {
    Text(
        text = AnnotatedString.fromMarkdown(
            markdownString = """
                # H1
                ## H2
                ### H3
                #### H4
                ##### H5
                ###### H6
                
                H1
                ==
                
                H2
                --

                * Item 1
                * Item 2
                    * Item 2a
                    * Item 2b

                1. Item 1
                2. Item 2
                    a. Item 2a
                    a. Item 2b

                > LegacyBlockquotes1
                >> LegacyBlockquotes1
                
                ```kotlin
                @Composable
                fun Component(modifier: Modifier = Modifier) {
                    //
                }
                ```
                
                *emph*  
                _emph_

                **strong**  
                __strong__

                _emph **strong-emph**_
                
                [link](https://example.com)

                Inline `code1`.
                *Inline `code2`.*
                **Inline `code3`.**
                _**Inline `code4`.**_
            """.trimIndent(),
        ),
    )
}
