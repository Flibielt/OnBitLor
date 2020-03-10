package hu.flibielt.onbitlor.repository;

import hu.flibielt.onbitlor.entity.TestResult;
import hu.flibielt.onbitlor.entity.TestResultId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultRepository extends JpaRepository<TestResult, TestResultId> {
}
