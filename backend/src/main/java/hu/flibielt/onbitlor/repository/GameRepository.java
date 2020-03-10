package hu.flibielt.onbitlor.repository;

import hu.flibielt.onbitlor.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

    Game findById(long id);
    Game findByName(String name);

}
