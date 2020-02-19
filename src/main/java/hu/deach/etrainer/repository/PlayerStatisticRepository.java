package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.PlayerStatistic;
import hu.deach.etrainer.entity.PlayerStatisticId;
import org.springframework.data.repository.CrudRepository;

public interface PlayerStatisticRepository extends CrudRepository<PlayerStatistic, PlayerStatisticId> {
}
