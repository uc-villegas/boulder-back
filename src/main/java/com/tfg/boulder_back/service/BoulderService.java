package com.tfg.boulder_back.service;

import com.tfg.boulder_back.domain.request.UpdateBoulderRequest;
import com.tfg.boulder_back.domain.request.UpdateVideoRequest;
import com.tfg.boulder_back.entity.Boulder;
import com.tfg.boulder_back.entity.Video;

import java.util.List;

public interface BoulderService {

    Boulder addBoulder(Boulder boulder);
    List<Boulder> findAllBoulders();
    Boulder editBoulder(Long idBoulder, UpdateBoulderRequest updateBoulderRequest);


}
