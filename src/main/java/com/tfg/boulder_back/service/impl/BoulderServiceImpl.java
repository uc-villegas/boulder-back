package com.tfg.boulder_back.service.impl;

import com.tfg.boulder_back.domain.request.UpdateBoulderRequest;
import com.tfg.boulder_back.entity.Boulder;
import com.tfg.boulder_back.entity.Video;
import com.tfg.boulder_back.exceptions.BoulderNotFoundException;
import com.tfg.boulder_back.exceptions.VideoNotFoundException;
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
    public List<Boulder> findAllBoulders() {
        return boulderRepository.findAll();
    }

    @Override
    public Boulder editBoulder(Long idBoulder, UpdateBoulderRequest updateBoulderRequest) {
        Optional<Boulder> optionalBoulder = boulderRepository.findById(idBoulder);
        if (optionalBoulder.isPresent()) {
            Boulder boulder = optionalBoulder.get();
            boulder.setName(updateBoulderRequest.getName());
            boulder.setLocality(updateBoulderRequest.getLocality());
            boulder.setMail(updateBoulderRequest.getMail());
            boulder.setPhone(updateBoulderRequest.getPhone());
            boulder.setAddress(updateBoulderRequest.getAddress());
            boulder.setPhone2(updateBoulderRequest.getPhone2());
            return boulderRepository.save(boulder);
        } else {
            throw new BoulderNotFoundException("Boulder not found with id " + idBoulder);
        }
    }

}
