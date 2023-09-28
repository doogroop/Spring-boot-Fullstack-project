package com.cpan252.assignmentOne.cpan252assignmentOne.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cpan252.assignmentOne.cpan252assignmentOne.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
