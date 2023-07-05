package com.simple.simplepass.repository;

import com.simple.simplepass.model.SimpleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<SimpleUser, Long> {

    Optional<SimpleUser> findByEmail(String email);
}
