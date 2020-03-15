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

import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public PlayerSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        ArrayList<String> authorities = new ArrayList<>();
        for (int i = 0; i < currentUser.getAuthorities().size(); i++) {
            authorities.add(currentUser.getAuthorities().stream().collect(Collectors.toCollection(ArrayList::new)).get(i).toString());
        }
        return new PlayerSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getFirstName(), currentUser.getLastName(), currentUser.getBit(), authorities);
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

    @GetMapping("/checkUsernameAvailability/{username}")
    public PlayerIdentityAvailability checkUsernameAvailability(@PathVariable(value = "username") String username) {
        Boolean isAvailable = !playerService.existsByUsername(username);
        return new PlayerIdentityAvailability(isAvailable);
    }

    @GetMapping("/checkEmailAvailability/{email}")
    public PlayerIdentityAvailability checkEmailAvailability(@PathVariable(value = "email") String email) {
        Boolean isAvailable = !playerService.existsByEmail(email);
        return new PlayerIdentityAvailability(isAvailable);
    }

}
