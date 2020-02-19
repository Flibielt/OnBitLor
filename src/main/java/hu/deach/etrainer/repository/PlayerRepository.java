package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}
