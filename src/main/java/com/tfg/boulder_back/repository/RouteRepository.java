package com.tfg.boulder_back.repository;

import com.tfg.boulder_back.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    /**
     * Selecciona ciertos campos específicos de la entidad Route
     * @param idBoulder idBoulder de las vias
     * @return List<RouteDTO>
     */

    @Query("SELECT r FROM Route r WHERE r.boulder.idBoulder = :idBoulder")
    List<Route> findAllByIdBoulder(@Param("idBoulder") Long idBoulder);

    Optional<Route> findByName(String routeName);

    boolean existsByQrRoute(String qrRoute);
    boolean existsByName(String name);
}
