package com.tfg.boulder_back.service.impl;

import com.tfg.boulder_back.entity.Boulder;
import com.tfg.boulder_back.repository.BoulderRepository;
import com.tfg.boulder_back.service.BoulderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Boulder> findAllBoulders() {
        return boulderRepository.findAll();
    }

}
