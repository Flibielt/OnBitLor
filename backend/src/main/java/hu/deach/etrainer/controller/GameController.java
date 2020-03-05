package hu.deach.etrainer.controller;

import hu.deach.etrainer.dto.GameDto;
import hu.deach.etrainer.model.ApiResponse;
import hu.deach.etrainer.model.GameRequest;
import hu.deach.etrainer.model.JoinedGame;
import hu.deach.etrainer.security.CurrentUser;
import hu.deach.etrainer.security.UserPrincipal;
import hu.deach.etrainer.service.GameService;
import hu.deach.etrainer.service.InGameNameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private InGameNameService inGameNameService;

    @PostMapping
    public ResponseEntity<?> addGame(@RequestBody GameRequest request) {
        GameDto gameDto = gameService.save(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{gameId}")
                .buildAndExpand(gameDto.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Game successfully created"));
    }

    @GetMapping("/all")
    public ArrayList<GameDto> getAllGame() {
        return gameService.findAll();
    }

    @GetMapping("/joined")
    public ArrayList<JoinedGame> getJoinedGames(@CurrentUser UserPrincipal currentUser) {
        return inGameNameService.findByPlayer(currentUser.getId());
    }

    @GetMapping("/{gameId}")
    public GameDto getGameById(@PathVariable(value = "gameId") Long gameId) {
        return gameService.findById(gameId);
    }

}
