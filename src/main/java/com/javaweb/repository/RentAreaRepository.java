package com.javaweb.repository;

import com.javaweb.entity.AreasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentAreaRepository extends JpaRepository<AreasEntity, Long> {
    void deleteAllByBuildingId(Long id);
}
