package hu.flibielt.onbitlor.repository;

import hu.flibielt.onbitlor.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {

    Test findByName(String name);

}
