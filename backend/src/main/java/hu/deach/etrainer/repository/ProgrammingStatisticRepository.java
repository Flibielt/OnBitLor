package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.ProgrammingStatistic;
import hu.deach.etrainer.entity.ProgrammingStatisticId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;

public interface ProgrammingStatisticRepository extends JpaRepository<ProgrammingStatistic, Long> {

    @Query("SELECT p FROM ProgrammingStatistic p WHERE p.date = :date")
    ArrayList<ProgrammingStatistic> findAllByDate(@Param("date")Date date);

}
