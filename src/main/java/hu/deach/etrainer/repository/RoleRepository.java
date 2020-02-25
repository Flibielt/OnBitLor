package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
