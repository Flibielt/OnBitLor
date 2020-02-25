package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.PlayerStatistic;
import hu.deach.etrainer.entity.PlayerStatisticId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerStatisticRepository extends JpaRepository<PlayerStatistic, PlayerStatisticId> {
}
