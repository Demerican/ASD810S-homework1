package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.identifier

@JvmInline
value class word(val word: String)

data class Card(
    val id: identifier, val words: List<word>
)