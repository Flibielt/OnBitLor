package hu.flibielt.onbitlor.repository;

import hu.flibielt.onbitlor.entity.TestResult;
import hu.flibielt.onbitlor.entity.TestResultId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    @Query("SELECT t from TestResult t WHERE t.test.id = :testId ORDER BY t.result DESC")
    ArrayList<TestResult> findAllByTestIdOrderByResultDesc(@Param("testId") Long testId);
}
