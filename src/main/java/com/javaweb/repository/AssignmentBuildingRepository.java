package com.javaweb.repository;

import com.javaweb.entity.AssignmentBuilding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuilding, Long> {
    @Transactional
    void deleteByBuildingId(Long buildingId);
}
