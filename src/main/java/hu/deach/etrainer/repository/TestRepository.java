package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
