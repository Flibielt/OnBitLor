package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
