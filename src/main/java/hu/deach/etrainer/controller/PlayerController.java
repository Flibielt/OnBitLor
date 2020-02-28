package hu.deach.etrainer.controller;

import hu.deach.etrainer.dto.PlayerDto;
import hu.deach.etrainer.entity.Player;
import hu.deach.etrainer.exception.ResourceNotFoundException;
import hu.deach.etrainer.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    public PlayerDto getUserProfile(@PathVariable(value = "username") String username) {
        return playerService.findByUsername(username);
    }

}
