package hu.flibielt.onbitlor.repository;

import hu.flibielt.onbitlor.entity.Programming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface ProgrammingRepository extends JpaRepository<Programming, Long> {
    Boolean existsByName(String name);
    Programming findByName(String name);
}
