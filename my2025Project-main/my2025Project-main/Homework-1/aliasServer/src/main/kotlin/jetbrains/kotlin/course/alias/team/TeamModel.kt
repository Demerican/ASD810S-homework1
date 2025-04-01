package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.identifier

data class Team(
    val id: identifier,var points: Int = 0, val name: String, val members: List<String>

)