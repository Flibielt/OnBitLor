package hu.flibielt.onbitlor.controller;

import hu.flibielt.onbitlor.dto.PlayerDto;
import hu.flibielt.onbitlor.exception.ResourceNotFoundException;
import hu.flibielt.onbitlor.model.PlayerIdentityAvailability;
import hu.flibielt.onbitlor.model.PlayerSummary;
import hu.flibielt.onbitlor.security.CurrentUser;
import hu.flibielt.onbitlor.security.UserPrincipal;
import hu.flibielt.onbitlor.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public PlayerSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new PlayerSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
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

    @GetMapping("/checkUsernameAvailability")
    public PlayerIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !playerService.existsByEmail(username);
        return new PlayerIdentityAvailability(isAvailable);
    }

    @GetMapping("/checkEmailAvailability")
    public PlayerIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !playerService.existsByEmail(email);
        return new PlayerIdentityAvailability(isAvailable);
    }

}
