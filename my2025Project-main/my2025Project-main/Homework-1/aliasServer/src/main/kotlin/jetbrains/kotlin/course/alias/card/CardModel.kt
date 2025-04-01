package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.identifier

@JvmInline
value class Word(val word: String)

data class Card(
    val id: identifier, val words: List<Word>
)