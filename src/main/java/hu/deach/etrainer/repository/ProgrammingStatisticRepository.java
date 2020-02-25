package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.ProgrammingStatistic;
import hu.deach.etrainer.entity.ProgrammingStatisticId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammingStatisticRepository extends JpaRepository<ProgrammingStatistic, ProgrammingStatisticId> {
}
