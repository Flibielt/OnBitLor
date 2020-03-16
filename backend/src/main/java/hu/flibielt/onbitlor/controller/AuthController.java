package hu.flibielt.onbitlor.controller;

import hu.flibielt.onbitlor.security.JwtTokenProvider;
import hu.flibielt.onbitlor.entity.Player;
import hu.flibielt.onbitlor.entity.Role;
import hu.flibielt.onbitlor.entity.RoleName;
import hu.flibielt.onbitlor.exception.AppException;
import hu.flibielt.onbitlor.model.ApiResponse;
import hu.flibielt.onbitlor.model.JwtAuthenticationResponse;
import hu.flibielt.onbitlor.model.LoginRequest;
import hu.flibielt.onbitlor.model.SignUpRequest;
import hu.flibielt.onbitlor.repository.PlayerRepository;
import hu.flibielt.onbitlor.repository.RoleRepository;
import hu.flibielt.onbitlor.service.PlayerService;
import hu.flibielt.onbitlor.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PlayerService playerService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(playerService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(playerService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating player's account
        Player player = new Player();

        player.setFirstName(signUpRequest.getFirstName());
        player.setLastName(signUpRequest.getLastName());
        player.setUsername(signUpRequest.getUsername());
        player.setEmail(signUpRequest.getEmail());
        player.setBit(0);

        player.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        player.setRoles(Collections.singleton(userRole));

        Player result = playerRepository.save(player);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/players/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
