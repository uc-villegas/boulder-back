package com.tfg.boulder_back.service;

import com.tfg.boulder_back.entity.Boulder;

import java.util.List;
import java.util.Optional;

public interface BoulderService {

    Boulder addBoulder(Boulder boulder);
    Optional<Boulder> findByIdBoulder(Long idBoulder);
    List<Boulder> findAllBoulders();
    Boulder updateBoulder(Boulder boulder);
    void deleteBoulder(Long idBoulder);
}
