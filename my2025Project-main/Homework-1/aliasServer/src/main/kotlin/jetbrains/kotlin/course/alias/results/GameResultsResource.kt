package jetbrains.kotlin.course.alias.results

import alias.JsTeam
import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService
import jetbrains.kotlin.course.alias.util.toGameResult
import org.springframework.web.bind.annotation.*

// We can not use a typealias here because the Spring framework can not parse it
class GameJsResult : ArrayList<JsTeam>()

typealias GameResults = List<Team>

@RestController
@RequestMapping("/api/results/")
class GameResultsResource(val service: GameResultsService) {

    companion object {
        val gameHistory: MutableList<GameResultsResource> = mutableListOf()
    }

    @CrossOrigin
    @PostMapping("/save")
    fun saveGameResults(@RequestBody result: GameJsResult) {
        if (result.isEmpty()) {
            throw IllegalArgumentException("Game results should not be empty.")
        }

        val teamServiceTeam = TeamService.teamsStorage.keys
        for (team in result) {
            if (team.id !in teamServiceTeam) {
                throw IllegalArgumentException("Team with ID '${team.id}' does not exist.")
            }
        }
        gameHistory.add(result)
    }

    @CrossOrigin
    @GetMapping("/all")
    fun getAllGameResults() = service.getAllGameResults()


}
