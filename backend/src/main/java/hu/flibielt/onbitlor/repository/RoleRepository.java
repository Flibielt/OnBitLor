package hu.flibielt.onbitlor.repository;

import hu.flibielt.onbitlor.entity.Role;
import hu.flibielt.onbitlor.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
