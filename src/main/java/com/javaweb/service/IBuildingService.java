package com.javaweb.service;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.*;

public interface IBuildingService {
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);
    BuildingDTO findById(Long id);
    void saveBuilding(BuildingDTO buildingDTO);
    ResponseDTO typeChecked(Long buildingID);
    ResponseDTO listStaff(Long buildingID);
    void deleteBuilding(List<Long> buildingId);
    List<BuildingSearchResponse> getBuildings(BuildingSearchRequest request, Pageable pageable);
    int totalItems(BuildingSearchRequest request);
//    List<BuildingSearchResponse> findAll();

}
