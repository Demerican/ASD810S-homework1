package jetbrains.kotlin.course.alias.util

typealias identifier = String

class identifierFactory {
    private  var count = 0


    fun generate(): identifier {
        count++
        return "id_$count"
    }
}

fun uniqueIdentifier(): identifier {
    return identifierFactory().generate()
}