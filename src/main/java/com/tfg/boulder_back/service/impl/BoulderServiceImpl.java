package com.tfg.boulder_back.service.impl;

import com.tfg.boulder_back.dto.BouldersInfo;
import com.tfg.boulder_back.dto.RouteInfo;
import com.tfg.boulder_back.entity.Boulder;
import com.tfg.boulder_back.repository.BoulderRepository;
import com.tfg.boulder_back.service.BoulderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<BouldersInfo> findAllBouldersAndRoutes() {
        List<Boulder> boulders = boulderRepository.findAll();

        for (Boulder boulder : boulders) {
            System.out.println("Boulder: " + boulder.getName() + " - Routes: " + boulder.getRoutes());
        }

        // Convertir los boulders a BouldersInfo
        return boulders.stream()
                .map(boulder -> {
                    List<RouteInfo> routeInfos = boulder.getRoutes().stream()
                            .map(route -> new RouteInfo(route.getName()))
                            .collect(Collectors.toList());

                    return new BouldersInfo(boulder.getName(), routeInfos);
                })
                .collect(Collectors.toList());
    }

}
