package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.PlayerStatistic;
import hu.deach.etrainer.entity.PlayerStatisticId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface PlayerStatisticRepository extends JpaRepository<PlayerStatistic, PlayerStatisticId> {

    @Query("SELECT p FROM PlayerStatistic p WHERE p.player.id = :playerId")
    ArrayList<PlayerStatistic> findAllByPlayer(@Param("playerId") Long playerId);

    @Query("SELECT p FROM  PlayerStatistic p where p.player.id = :gameId")
    ArrayList<PlayerStatistic> findAllByGame(@Param("gameId") Long gameId);

}