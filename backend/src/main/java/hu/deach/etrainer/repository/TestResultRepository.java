package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.TestResult;
import hu.deach.etrainer.entity.TestResultId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultRepository extends JpaRepository<TestResult, TestResultId> {
}
