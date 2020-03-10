package hu.flibielt.onbitlor.controller;

import hu.flibielt.onbitlor.dto.GameDto;
import hu.flibielt.onbitlor.model.ApiResponse;
import hu.flibielt.onbitlor.model.GameRequest;
import hu.flibielt.onbitlor.model.JoinedGame;
import hu.flibielt.onbitlor.security.CurrentUser;
import hu.flibielt.onbitlor.security.UserPrincipal;
import hu.flibielt.onbitlor.service.GameService;
import hu.flibielt.onbitlor.service.InGameNameService;
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
