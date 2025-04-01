package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.toArrayJsTeams
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/teams/")
class TeamResource(val service: TeamService) {
    @CrossOrigin
    @PostMapping("/generate")
    fun generateTeamsForOneRound(@RequestBody number: Int, availableMemberNames: List<String>) =

        service.generateTeamsForOneRound(number, availableMemberNames ).toArrayJsTeams()
}
