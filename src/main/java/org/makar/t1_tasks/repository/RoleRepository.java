package org.makar.t1_tasks.repository;

import org.makar.t1_tasks.model.role.Role;
import org.makar.t1_tasks.model.role.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleEnum name);
}
