package hu.flibielt.onbitlor.repository;

import hu.flibielt.onbitlor.entity.IgnId;
import hu.flibielt.onbitlor.entity.InGameName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface IgnRepository extends JpaRepository<InGameName, IgnId> {

    ArrayList<InGameName> findAllByPlayerId(Long playerId);
}
