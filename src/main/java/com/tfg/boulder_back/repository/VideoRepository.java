package com.tfg.boulder_back.repository;

import com.tfg.boulder_back.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findByRouteId(Long routeId);

    @Query("SELECT v FROM Video v WHERE v.user.id = :userId ")
    List<Video> findByUserId(Long userId);
}
