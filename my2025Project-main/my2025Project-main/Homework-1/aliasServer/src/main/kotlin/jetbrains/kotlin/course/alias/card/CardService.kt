package jetbrains.kotlin.course.alias.card

import org.springframework.stereotype.Service
import jetbrains.kotlin.course.alias.util.identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory
import jetbrains.kotlin.course.alias.util.words

@Service
class CardService(private val words: List<String>) {


    val identifierFactory: IdentifierFactory = IdentifierFactory()
    val cards: List<Card> = generateCards()

    companion object{

        const val WORDS_IN_CARD = 4
        val cardsAmount: Int by lazy { words.size / WORDS_IN_CARD}
    }


    private fun generateCards(): List<Card> {
        val shuffledWords = words.shuffled()
        return shuffledWords.chunked(WORDS_IN_CARD).take(cardsAmount).map { wordList -> Card(id = identifierFactory.generate(), words = wordList.toWords())
        }
    }

    private fun List<String>.toWords(): List<Word> = this.map { Word(it) }

    fun getCardByIndex(index: Int): Card {
        if (index < 0 || index >= cards.size) {
            throw IndexOutOfBoundsException("Card index $index is out of bounds (0,${cards.lastIndex})")

        }
        return cards[index]
    }
}
