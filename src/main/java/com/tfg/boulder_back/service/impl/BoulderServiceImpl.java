package com.tfg.boulder_back.service.impl;

import com.tfg.boulder_back.entity.Boulder;
import com.tfg.boulder_back.repository.BoulderRepository;
import com.tfg.boulder_back.service.BoulderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BoulderServiceImpl implements BoulderService {

    private final BoulderRepository boulderRepository;

    public BoulderServiceImpl(BoulderRepository boulderRepository) {
        this.boulderRepository = boulderRepository;
    }

    @Override
    public Boulder addBoulder(Boulder boulder) {
       return boulderRepository.save(boulder);
    }

    @Override
    public Optional<Boulder> findByIdBoulder(Long idBoulder) {
        return Optional.empty();
    }

    @Override
    public List<Boulder> findAllBoulders() {
        return boulderRepository.findAll();
    }

    @Override
    public Boulder updateBoulder(Boulder boulder) {
        return null;
    }

    @Override
    public void deleteBoulder(Long idBoulder) {

    }
}
