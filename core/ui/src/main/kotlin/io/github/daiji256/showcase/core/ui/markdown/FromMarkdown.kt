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
    style: MarkdownStyle = MarkdownStyle.Default,
): AnnotatedString =
    buildAnnotatedString {
        val flavour = CommonMarkFlavourDescriptor()
        val node = MarkdownParser(flavour).buildMarkdownTreeFromString(markdownString)
        val blocks = node.getBlocks(markdownString)
        blocks.forEachIndexed { index, block ->
            // Add spacing between paragraphs
            if (index != 0) withStyle(style = ParagraphStyle()) {}
            append(block = block, style = style)
        }
    }

@Immutable
data class MarkdownStyle(
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
    val codeBlock: SpanStyle,
) {
    companion object {
        val Default = MarkdownStyle(
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
            codeBlock = SpanStyle(fontFamily = FontFamily.Monospace, fontSize = 0.875.em),
        )
    }
}

private fun ASTNode.getBlocks(markdownString: String): List<Block> =
    when (this.type) {
        MarkdownElementTypes.MARKDOWN_FILE ->
            children.flatMap { it.getBlocks(markdownString) }

        MarkdownElementTypes.PARAGRAPH,
        MarkdownElementTypes.UNORDERED_LIST,
        MarkdownElementTypes.ORDERED_LIST,
        MarkdownElementTypes.BLOCK_QUOTE,
        ->
            listOf(
                Block.Paragraph(
                    contents = children.mapNotNull {
                        it.getContent(markdownString)
                    },
                ),
            )

        MarkdownElementTypes.SETEXT_1,
        MarkdownElementTypes.SETEXT_2,
        ->
            listOf(
                Block.h(
                    type = this.type,
                    contents = children
                        .dropLast(2) // === or --- and EOL
                        .mapNotNull { it.getContent(markdownString) },
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
                Block.h(
                    type = this.type,
                    contents = children
                        .drop(1) // # or ## or ### or #### or ##### or ######
                        .mapNotNull { it.getContent(markdownString) },
                ),
            )

        MarkdownElementTypes.CODE_FENCE ->
            listOf(
                Block.Code(
                    contents = children
                        .filterNot { it.type == MarkdownTokenTypes.CODE_FENCE_START }
                        .filterNot { it.type == MarkdownTokenTypes.CODE_FENCE_END }
                        .filterNot { it.type == MarkdownTokenTypes.FENCE_LANG }
                        .dropWhile { it.type == MarkdownTokenTypes.EOL }
                        .dropLastWhile { it.type == MarkdownTokenTypes.EOL }
                        .mapNotNull { it.getContent(markdownString) },
                ),
            )

        else -> emptyList()
    }

private fun ASTNode.getContent(markdownString: String): Content? =
    when (this.type) {
        MarkdownTokenTypes.FENCE_LANG ->
            null

        MarkdownElementTypes.EMPH ->
            Content.Emph(
                contents = children
                    .drop(1) // * or _
                    .dropLast(1) // * or _
                    .mapNotNull { it.getContent(markdownString) },
            )

        MarkdownElementTypes.STRONG ->
            Content.Strong(
                contents = children
                    .drop(2) // ** or __
                    .dropLast(2) // ** or __
                    .mapNotNull { it.getContent(markdownString) },
            )

        MarkdownElementTypes.CODE_SPAN ->
            Content.Code(
                contents = children
                    .drop(1) // `
                    .dropLast(1) // `
                    .mapNotNull { it.getContent(markdownString) },
            )

        MarkdownElementTypes.INLINE_LINK -> run {
            Content.Link(
                contents = children.find { it.type == MarkdownElementTypes.LINK_TEXT }?.children
                    ?.drop(1) // [
                    ?.dropLast(1) // ]
                    ?.mapNotNull { it.getContent(markdownString) }
                    ?: return@run null,
                url = children.findLast { it.type == MarkdownElementTypes.LINK_DESTINATION }
                    ?.getTextInNode(markdownString)?.toString()
                    ?: return@run null,
            )
        }

        MarkdownTokenTypes.ATX_CONTENT ->
            Content.Text(text = getTextInNode(markdownString).toString().trimStart())

        else ->
            Content.Text(text = getTextInNode(markdownString).toString())
    }

private sealed interface Block {
    val contents: List<Content>

    data class Paragraph(override val contents: List<Content>) : Block
    data class H1(override val contents: List<Content>) : Block
    data class H2(override val contents: List<Content>) : Block
    data class H3(override val contents: List<Content>) : Block
    data class H4(override val contents: List<Content>) : Block
    data class H5(override val contents: List<Content>) : Block
    data class H6(override val contents: List<Content>) : Block
    data class Code(override val contents: List<Content>) : Block

    companion object {
        fun h(type: IElementType, contents: List<Content>): Block =
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

private sealed interface Content {
    data class Text(val text: String) : Content
    data class Emph(val contents: List<Content>) : Content
    data class Strong(val contents: List<Content>) : Content
    data class Code(val contents: List<Content>) : Content
    data class Link(val contents: List<Content>, val url: String) : Content
}

private fun AnnotatedString.Builder.append(block: Block, style: MarkdownStyle) {
    withStyle(style = ParagraphStyle()) {
        withStyle(
            style = when (block) {
                is Block.Paragraph -> SpanStyle()
                is Block.H1 -> style.h1
                is Block.H2 -> style.h2
                is Block.H3 -> style.h3
                is Block.H4 -> style.h4
                is Block.H5 -> style.h5
                is Block.H6 -> style.h6
                is Block.Code -> style.codeBlock
            },
        ) {
            block.contents.forEach {
                append(content = it, style = style)
            }
        }
    }
}

private fun AnnotatedString.Builder.append(content: Content, style: MarkdownStyle) {
    when (content) {
        is Content.Text ->
            append(text = content.text)

        is Content.Emph ->
            withStyle(style = style.emph) {
                content.contents.forEach {
                    append(content = it, style = style)
                }
            }

        is Content.Strong ->
            withStyle(style = style.strong) {
                content.contents.forEach {
                    append(content = it, style = style)
                }
            }

        is Content.Code ->
            withStyle(style = style.code) {
                content.contents.forEach {
                    append(content = it, style = style)
                }
            }

        is Content.Link ->
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

                > Blockquotes1
                >> Blockquotes1
                
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
