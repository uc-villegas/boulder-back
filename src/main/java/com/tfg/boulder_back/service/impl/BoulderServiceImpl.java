package com.tfg.boulder_back.service.impl;

import com.tfg.boulder_back.entity.Boulder;
import com.tfg.boulder_back.service.BoulderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BoulderServiceImpl implements BoulderService {

    @Override
    public Boulder addBoulder(Boulder boulder) {
        return null;
    }

    @Override
    public Optional<Boulder> findByIdBoulder(Long idBoulder) {
        return Optional.empty();
    }

    @Override
    public List<Boulder> findAllBoulders() {
        return List.of();
    }

    @Override
    public Boulder updateBoulder(Boulder boulder) {
        return null;
    }

    @Override
    public void deleteBoulder(Long idBoulder) {

    }
}
