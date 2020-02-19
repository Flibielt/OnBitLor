package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.AiCompetitionStatistic;
import hu.deach.etrainer.entity.AiCompetitionStatisticId;
import org.springframework.data.repository.CrudRepository;

public interface AiCompetitionStatisticRepository extends CrudRepository<AiCompetitionStatistic, AiCompetitionStatisticId> {
}
