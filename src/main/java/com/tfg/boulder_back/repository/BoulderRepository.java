package com.tfg.boulder_back.repository;

import com.tfg.boulder_back.entity.Boulder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoulderRepository extends JpaRepository<Boulder, Long> {

    @Query("SELECT b FROM Boulder b WHERE b.name = :nameBoulder ")
    Optional<Boulder> findByName(@Param("nameBoulder") String nameBoulder);
}
