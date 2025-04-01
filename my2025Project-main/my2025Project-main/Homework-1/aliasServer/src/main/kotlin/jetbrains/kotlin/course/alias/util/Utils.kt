package jetbrains.kotlin.course.alias.util

typealias identifier = Int

class IdentifierFactory {
    var count = 0
    fun generate(): identifier {
        count++
        return count
    }
}

fun uniqueIdentifier(): identifier {
    return IdentifierFactory().generate()
}