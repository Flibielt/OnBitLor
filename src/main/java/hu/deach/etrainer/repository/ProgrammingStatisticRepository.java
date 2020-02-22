package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.ProgrammingStatistic;
import hu.deach.etrainer.entity.ProgrammingStatisticId;
import org.springframework.data.repository.CrudRepository;

public interface ProgrammingStatisticRepository extends CrudRepository<ProgrammingStatistic, ProgrammingStatisticId> {
}
