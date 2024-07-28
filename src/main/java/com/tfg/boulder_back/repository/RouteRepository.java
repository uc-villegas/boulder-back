package com.tfg.boulder_back.repository;

import com.tfg.boulder_back.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    /**
     * Selecciona ciertos campos específicos de la entidad Route
     * @param idBoulder idBoulder de las vias
     * @return List<RouteDTO>
     */
//    @Query(
//            value =
//                    "SELECT " +
//                            "new com.tfg.boulder_back.entity.Route(r.idRoute, r.qrRoute, " +
//                            "r.name, r.type, r.num_nivel, r.presa, r.creationDate, r.videos, r.boulder) " +
//                    "FROM Route r " +
//                    "WHERE r.boulder.idBoulder = 1"
//    )
    @Query("SELECT r FROM Route r WHERE r.boulder.idBoulder = :idBoulder")
    List<Route> findAllByIdBoulder(@Param("idBoulder") Long idBoulder);
}
