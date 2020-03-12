package hu.flibielt.onbitlor.repository;

import hu.flibielt.onbitlor.entity.ProgrammingStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;

public interface ProgrammingStatisticRepository extends JpaRepository<ProgrammingStatistic, Long> {

    @Query("SELECT p FROM ProgrammingStatistic p WHERE p.date = :date")
    ArrayList<ProgrammingStatistic> findAllByDate(@Param("date")Date date);

    ArrayList<ProgrammingStatistic> getAllByOrderByScoreDesc();

    @Query("SELECT p from ProgrammingStatistic p WHERE p.programming.Id = :programmingId ORDER BY p.score DESC")
    ArrayList<ProgrammingStatistic> findAllByProgrammingIdOrderByScoreDesc(@Param("programmingId") Long programmingId);
}
