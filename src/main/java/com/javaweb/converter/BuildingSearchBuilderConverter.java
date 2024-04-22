package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.request.BuildingSearchRequest;
import org.springframework.stereotype.Component;

@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder builder = new BuildingSearchBuilder.BuildingBuilder()
                .setName(buildingSearchRequest.getName())
                .setFloorArea(buildingSearchRequest.getFloorArea())
                .setDirection(buildingSearchRequest.getDirection())
                .setDistrict(buildingSearchRequest.getDistrict())
                .setLevel(buildingSearchRequest.getLevel())
                .setManagerName(buildingSearchRequest.getManagerName())
                .setManagerPhone(buildingSearchRequest.getManagerPhone())
                .setNumberOfBasement(buildingSearchRequest.getNumberOfBasement())
                .setRentAreaFrom(buildingSearchRequest.getRentAreaFrom())
                .setRentAreaTo(buildingSearchRequest.getRentAreaTo())
                .setRentPriceFrom(buildingSearchRequest.getRentPriceFrom())
                .setRentPriceTo(buildingSearchRequest.getRentPriceTo())
                .setStaffId(buildingSearchRequest.getStaffId())
                .setStreet(buildingSearchRequest.getStreet())
                .setTypeCode(buildingSearchRequest.getTypeCode())
                .setWard(buildingSearchRequest.getWard())
                .build();
        return builder;
    }
}
