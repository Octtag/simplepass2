package com.simple.simplepass.persistance.repository;

import com.simple.simplepass.persistance.entities.SimpleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface SimpleUserRepository extends JpaRepository<SimpleUser, Long> {

    Optional<SimpleUser> findByEmail(String email);

    Boolean existsByEmail(String email);

}

