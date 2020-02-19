package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {

    Game findById(long id);

}
