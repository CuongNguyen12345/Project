package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;
    public BuildingEntity converToBuildingEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        return buildingEntity;
    }

    public List<String> convertRentArea(BuildingDTO buildingDTO) {
        List<String> list = Arrays.stream(buildingDTO.getRentArea().split(",")).collect(Collectors.toList());
        return list;
    }
}
