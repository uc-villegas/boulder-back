package com.tfg.boulder_back.service;

import com.tfg.boulder_back.entity.Boulder;

import java.util.List;

public interface BoulderService {

    Boulder addBoulder(Boulder boulder);
    List<Boulder> findAllBoulders();

}
