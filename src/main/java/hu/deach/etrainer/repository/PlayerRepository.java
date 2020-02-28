package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
