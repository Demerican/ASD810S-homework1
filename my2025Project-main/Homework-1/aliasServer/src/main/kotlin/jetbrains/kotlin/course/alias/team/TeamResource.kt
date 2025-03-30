package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.toArrayJsTeams
import org.springframework.web.bind.annotation.*
import jetbrains.kotlin.course.alias.util.identifier
import jetbrains.kotlin.course.alias.util.identifierFactory


@RestController
@RequestMapping("/api/teams/")
class TeamResource(val service: TeamService) {
    @CrossOrigin
    @PostMapping("/generate")
    fun generateTeamsForOneRound(@RequestBody number: Int) =
        service.generateTeamsForOneRound(number).toArrayJsTeams()
}
