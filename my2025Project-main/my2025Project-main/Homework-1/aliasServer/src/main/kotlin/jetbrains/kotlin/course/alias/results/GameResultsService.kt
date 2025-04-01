package jetbrains.kotlin.course.alias.results


import com.fasterxml.jackson.databind.ObjectMapper
import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService
import org.springframework.stereotype.Service
import java.io.File

typealias GameResult = List<Team>

@Service
class GameResultsService {

    companion object {
        val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    private val objectMapper = ObjectMapper()
    private val gameHistoryFile: File = File("gameHistory.json")

    fun saveGameResults(result: GameResult) {

        require(result.isNotEmpty()) { "Game results cannot be empty" }
        val invalidTeams = result.filter { it.id !in TeamService.teamsStorage.keys }
        require(invalidTeams.isEmpty()) { "Teams with IDs ${invalidTeams.joinToString { it.id.toString() }} not found." }

        gameHistory.add(result)
        objectMapper.writeValue(gameHistoryFile, gameHistory)
    }


    fun getAllGameResults(): List<GameResult> {
        if (gameHistoryFile.exists()) {
            gameHistory.addAll(objectMapper.readValue(gameHistoryFile, objectMapper.typeFactory.constructCollectionType(MutableList::class.java)))
        }

        return gameHistory.reversed()
    }
}
