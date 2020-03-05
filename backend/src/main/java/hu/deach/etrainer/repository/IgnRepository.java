package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.IgnId;
import hu.deach.etrainer.entity.InGameName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface IgnRepository extends JpaRepository<InGameName, IgnId> {

    ArrayList<InGameName> findAllByPlayerId(Long playerId);
}
