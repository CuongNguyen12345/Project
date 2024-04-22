package com.javaweb.repository.custom;

import java.util.*;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import org.springframework.data.domain.Pageable;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findAll(BuildingSearchBuilder builder);
    int totalItems(BuildingSearchBuilder builder);
//    List<BuildingEntity> getAllBuildings(Pageable pageable);
}
