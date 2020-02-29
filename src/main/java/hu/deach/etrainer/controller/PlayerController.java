package hu.deach.etrainer.controller;

import hu.deach.etrainer.dto.PlayerDto;
import hu.deach.etrainer.exception.ResourceNotFoundException;
import hu.deach.etrainer.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody PlayerDto playerDto) {
        playerDto.setPassword(bCryptPasswordEncoder.encode(playerDto.getPassword()));
        playerService.save(playerDto);
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public PlayerDto getUserProfile(@PathVariable(value = "username") String username) {
        PlayerDto playerDto = playerService.findByUsername(username);
        if (playerDto == null) {
            throw new ResourceNotFoundException("Player", "username", username);
        }
        return playerDto;
    }

}
