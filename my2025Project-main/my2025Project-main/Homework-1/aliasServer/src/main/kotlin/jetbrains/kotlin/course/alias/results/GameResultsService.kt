package jetbrains.kotlin.course.alias.results


import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService
import org.springframework.stereotype.Service

typealias GameResult = List<Team>

@Service
class GameResultsService {

    companion object {
        val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    fun saveGameResults(result: GameResult) {

        require(result.isNotEmpty()) { "Game results cannot be empty" }
        val invalidTeams = result.filter { it.id !in TeamService.teamsStorage.keys }
        require(invalidTeams.isEmpty()) { "Teams with IDs ${invalidTeams.joinToString { it.id.toString() }} not found." }

        gameHistory.add(result)
    }

    fun getAllGameResults(): List<GameResult> {
        return gameHistory.reversed()
    }
}
