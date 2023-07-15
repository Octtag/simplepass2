package com.simple.simplepass.persistance.repository;

import com.simple.simplepass.persistance.entities.SimpleProductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface SimpleProductorRepository extends JpaRepository<SimpleProductor, Long> {

    Optional<SimpleProductor> findByEmail(String email);

    Boolean existsByEmail(String email);
}
