package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.IgnId;
import hu.deach.etrainer.entity.InGameName;
import org.springframework.data.repository.CrudRepository;

public interface IgnRepository extends CrudRepository<InGameName, IgnId> {

}
