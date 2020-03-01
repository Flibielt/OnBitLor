package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.IgnId;
import hu.deach.etrainer.entity.InGameName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IgnRepository extends JpaRepository<InGameName, IgnId> {

}
