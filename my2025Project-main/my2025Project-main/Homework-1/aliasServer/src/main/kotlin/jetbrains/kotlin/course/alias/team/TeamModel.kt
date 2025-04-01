package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.identifier

data class Team(
    val id: identifier, val name: String, val members: List<String>

)