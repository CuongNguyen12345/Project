package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.utils.DistrictUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BuildingSearchConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchResponse toBuildingResponse(BuildingEntity buildingEntity) {
        String areas = buildingEntity.getAreas().stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        String district = DistrictUtils.checkDistrict(buildingEntity.getDistrict());
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntity, BuildingSearchResponse.class);
        buildingSearchResponse.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + district);
        buildingSearchResponse.setRentArea(areas);
        return buildingSearchResponse;
    }

    public BuildingDTO toBuildingDTO(BuildingEntity buildingEntity) {
        String areas = buildingEntity.getAreas().stream().map(item->item.getValue().toString()).collect(Collectors.joining(","));
        BuildingDTO dto = modelMapper.map(buildingEntity, BuildingDTO.class);
        dto.setRentArea(areas);
        return dto;
    }
}
