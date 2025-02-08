package io.github.daiji256.showcase.core.ui.markdown

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.MarkdownTokenTypes
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.ast.getTextInNode
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.parser.MarkdownParser

/*
 * TODO:
 *  UNORDERED_LIST, ORDERED_LIST, BLOCK_QUOTE, IMAGE, ...
 */

/**
 * Renders the markdown text
 *
 * @param markdown the Markdown text to be displayed
 * @param modifier the Modifier to be applied to this Markdown
 * @param style the style applied to the text
 * @param markdownStyle the style applied to Markdown emph, etc.
 */
@Composable
fun Markdown(
    markdown: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    markdownStyle: MarkdownStyle = MarkdownStyle.Default,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(markdownStyle.space),
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        Blocks(markdown).forEach { block ->
            Text(
                text = block.toAnnotatedString(style = markdownStyle),
                style = style,
            )
        }
    }
}

/**
 * The style applied to Markdown emph, etc.
 *
 * @property emph the style for emphasized text (e.g., italic)
 * @property strong the style for strong text (e.g., bold)
 * @property code the style for inline code snippets
 * @property link the styles for link
 * @property h1 the style for first-level header
 * @property h2 the style for second-level header
 * @property h3 the style for third-level header
 * @property h4 the style for fourth-level header
 * @property h5 the style for fifth-level header
 * @property h6 the style for sixth-level header
 * @property codeBlock the style for code block
 * @property space the space to be applied between markdown elements
 */
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
    val space: Dp,
) {
    companion object {
        val Default
            @Composable get() = MarkdownStyle(
                emph = SpanStyle(fontStyle = FontStyle.Italic),
                strong = SpanStyle(fontWeight = FontWeight.Bold),
                code = SpanStyle(fontFamily = FontFamily.Monospace),
                link = TextLinkStyles(
                    style = SpanStyle(
                        textDecoration = TextDecoration.Underline,
                        color = MaterialTheme.colorScheme.primary,
                    ),
                ),
                h1 = SpanStyle(fontSize = 1.75.em),
                h2 = SpanStyle(fontSize = 1.5.em),
                h3 = SpanStyle(fontSize = 1.375.em),
                h4 = SpanStyle(fontSize = 1.25.em),
                h5 = SpanStyle(fontSize = 1.125.em),
                h6 = SpanStyle(fontSize = 1.0625.em),
                codeBlock = SpanStyle(fontFamily = FontFamily.Monospace, fontSize = 0.875.em),
                space = 8.dp,
            )
    }
}

private fun ASTNode.getBlocks(markdownString: String): List<Block> =
    when (this.type) {
        MarkdownElementTypes.MARKDOWN_FILE ->
            children.flatMap { it.getBlocks(markdownString) }

        MarkdownElementTypes.PARAGRAPH ->
            listOf(
                Block.Paragraph(
                    contents = children
                        .mapNotNull { it.getContent(markdownString) },
                ),
            )

        MarkdownElementTypes.SETEXT_1 ->
            listOf(
                Block.H1(
                    contents = children
                        .dropLast(2) // drop === and EOL
                        .mapNotNull { it.getContent(markdownString) },
                ),
            )

        MarkdownElementTypes.SETEXT_2 ->
            listOf(
                Block.H2(
                    contents = children
                        .dropLast(2) // drop --- and EOL
                        .mapNotNull { it.getContent(markdownString) },
                ),
            )

        MarkdownElementTypes.ATX_1 ->
            listOf(
                Block.H1(
                    contents = children
                        .drop(1) // drop #
                        .mapNotNull { it.getContent(markdownString) },
                ),
            )

        MarkdownElementTypes.ATX_2 ->
            listOf(
                Block.H2(
                    contents = children
                        .drop(1) // drop ##
                        .mapNotNull { it.getContent(markdownString) },
                ),
            )

        MarkdownElementTypes.ATX_3 ->
            listOf(
                Block.H3(
                    contents = children
                        .drop(1) // drop ###
                        .mapNotNull { it.getContent(markdownString) },
                ),
            )

        MarkdownElementTypes.ATX_4 ->
            listOf(
                Block.H4(
                    contents = children
                        .drop(1) // drop ####
                        .mapNotNull { it.getContent(markdownString) },
                ),
            )

        MarkdownElementTypes.ATX_5 ->
            listOf(
                Block.H5(
                    contents = children
                        .drop(1) // drop #####
                        .mapNotNull { it.getContent(markdownString) },
                ),
            )

        MarkdownElementTypes.ATX_6 ->
            listOf(
                Block.H6(
                    contents = children
                        .drop(1) // drop ######
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
        MarkdownElementTypes.EMPH ->
            Content.Emph(
                contents = children
                    .drop(1) // drop * or _
                    .dropLast(1) // drop * or _
                    .mapNotNull { it.getContent(markdownString) },
            )

        MarkdownElementTypes.STRONG ->
            Content.Strong(
                contents = children
                    .drop(2) // drop ** or __
                    .dropLast(2) // drop ** or __
                    .mapNotNull { it.getContent(markdownString) },
            )

        MarkdownElementTypes.CODE_SPAN ->
            Content.Code(
                contents = children
                    .drop(1) // drop `
                    .dropLast(1) // drop `
                    .mapNotNull { it.getContent(markdownString) },
            )

        MarkdownElementTypes.INLINE_LINK -> run {
            Content.Link(
                contents = children.find { it.type == MarkdownElementTypes.LINK_TEXT }?.children
                    ?.drop(1) // drop [
                    ?.dropLast(1) // drop ]
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

@Immutable
private interface Blocks : List<Block> {
    companion object {
        private data class BlocksImpl(val value: List<Block>) : Blocks, List<Block> by value

        @Stable
        operator fun invoke(markdown: String): Blocks = BlocksImpl(
            value = run {
                val flavour = CommonMarkFlavourDescriptor()
                val node = MarkdownParser(flavour).buildMarkdownTreeFromString(markdown)
                node.getBlocks(markdown)
            },
        )
    }
}

@Immutable
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
}

@Immutable
private sealed interface Content {
    data class Text(val text: String) : Content
    data class Emph(val contents: List<Content>) : Content
    data class Strong(val contents: List<Content>) : Content
    data class Code(val contents: List<Content>) : Content
    data class Link(val contents: List<Content>, val url: String) : Content
}

@Stable
private fun Block.toAnnotatedString(style: MarkdownStyle): AnnotatedString =
    buildAnnotatedString {
        withStyle(
            style = when (this@toAnnotatedString) {
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
            contents.forEach { content ->
                append(content = content, style = style)
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
    Markdown(
        markdown = """
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
    )
}
