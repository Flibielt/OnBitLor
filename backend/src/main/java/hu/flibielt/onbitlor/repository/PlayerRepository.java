package hu.flibielt.onbitlor.repository;

import hu.flibielt.onbitlor.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByUsernameOrEmail(String username, String email);

    Optional<Player> findByUsername(String username);

    Optional<Player> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
