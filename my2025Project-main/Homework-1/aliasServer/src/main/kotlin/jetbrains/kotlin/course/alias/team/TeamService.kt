package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.identifier
import jetbrains.kotlin.course.alias.util.identifierFactory
import org.springframework.stereotype.Service

@Service
class TeamService {

    val identifierFactory: identifierFactory = identifierFactory()

    companion object {
        val teamsStorage: MutableMap<identifier, Team> = mutableMapOf()
    }

    fun generateTeamsForOneRound(teamsNumber: Int, availableMemberNames: List<String>): List<Team> {
        val generatedTeams = mutableListOf<Team>()
        val shuffledMemberNames = availableMemberNames.shuffled()
        val memberPerTeam = shuffledMemberNames.size / teamsNumber
        val remainderMembers = shuffledMemberNames.size % teamsNumber

        for (i in 0 until teamsNumber) {
            val teamId = identifierFactory.generate()
            val teamName = "Team ${i + 1}"
            val startIndex = (i * memberPerTeam + minOf(i, remainderMembers))
            val endIndex = (i + 1) * memberPerTeam + minOf(i + 1, remainderMembers)
            val teamMembers = shuffledMemberNames.subList(startIndex, endIndex)

            val newTeam = Team(teamId, teamName, teamMembers)
            teamsStorage[teamId] = newTeam
            generatedTeams.add(newTeam)
        }
        return generatedTeams
    }

}
