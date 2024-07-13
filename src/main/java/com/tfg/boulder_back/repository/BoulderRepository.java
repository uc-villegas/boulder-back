package com.tfg.boulder_back.repository;

import com.tfg.boulder_back.entity.Boulder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoulderRepository extends JpaRepository<Boulder, Long> {
}
