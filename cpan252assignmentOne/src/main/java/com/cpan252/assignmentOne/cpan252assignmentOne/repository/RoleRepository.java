package com.cpan252.assignmentOne.cpan252assignmentOne.repository;

import com.cpan252.assignmentOne.cpan252assignmentOne.model.Role;
import com.cpan252.assignmentOne.cpan252assignmentOne.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
