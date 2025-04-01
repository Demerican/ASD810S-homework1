package jetbrains.kotlin.course.alias.team

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jetbrains.kotlin.course.alias.util.identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory
import org.springframework.stereotype.Service
import java.io.File

@Service
class TeamService {

    val identifierFactory: IdentifierFactory = IdentifierFactory()
    private val objectMapper = jacksonObjectMapper()
    private val teamsStorageFile = File("teamsStorage.json")
    private val teamIdCounterFile = File("teamIdCounter.txt")

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

            val newTeam = Team(teamId, 0,teamName, teamMembers)
            teamsStorage[teamId] = newTeam
            generatedTeams.add(newTeam)
        }
        return generatedTeams
    }

    fun saveTeamsData() {
        if (teamsStorageFile.exists()) {
            val loadedStorage: MutableMap<identifier, Team> = objectMapper.readValue(teamsStorageFile, objectMapper.typeFactory.constructCollectionType(MutableMap::class.java, identifier::class.java, Team::class.java))
            teamsStorage.putAll(loadedStorage)

        }
        if (teamIdCounterFile.exists()) {
            identifierFactory.count = teamIdCounterFile.readText().toIntOrNull() ?: 0
        }
    }
}
