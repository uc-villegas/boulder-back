package com.tfg.boulder_back.repository;

import com.tfg.boulder_back.entity.User;
import com.tfg.boulder_back.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}
