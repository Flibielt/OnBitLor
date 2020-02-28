package hu.deach.etrainer.repository;

import hu.deach.etrainer.entity.Role;
import hu.deach.etrainer.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
