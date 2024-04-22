package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
    private String name;
    private Long floorArea;
    private String district;
    private String ward;
    private String street;
    private Long numberOfBasement;
    private String direction;
    private Long level;
    private Long rentAreaFrom;
    private Long rentAreaTo;
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private String managerName;
    private String managerPhone;
    private Long staffId;
    private List<String> typeCode;

    private BuildingSearchBuilder(BuildingBuilder buildingBuilder) {
        this.name = buildingBuilder.name;
        this.floorArea = buildingBuilder.floorArea;
        this.district = buildingBuilder.district;
        this.ward = buildingBuilder.ward;
        this.street = buildingBuilder.street;
        this.numberOfBasement = buildingBuilder.numberOfBasement;
        this.direction = buildingBuilder.direction;
        this.level = buildingBuilder.level;
        this.rentAreaFrom = buildingBuilder.rentAreaFrom;
        this.rentAreaTo = buildingBuilder.rentAreaTo;
        this.rentPriceFrom = buildingBuilder.rentPriceFrom;
        this.rentPriceTo = buildingBuilder.rentPriceTo;
        this.managerName = buildingBuilder.managerName;
        this.managerPhone = buildingBuilder.managerPhone;
        this.staffId = buildingBuilder.staffId;
        this.typeCode = buildingBuilder.typeCode;
    }

    public String getName() {
        return name;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public String getDistrict() {
        return district;
    }

    public String getWard() {
        return ward;
    }

    public String getStreet() {
        return street;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public String getDirection() {
        return direction;
    }

    public Long getLevel() {
        return level;
    }

    public Long getRentAreaFrom() {
        return rentAreaFrom;
    }

    public Long getRentAreaTo() {
        return rentAreaTo;
    }

    public Long getRentPriceFrom() {
        return rentPriceFrom;
    }

    public Long getRentPriceTo() {
        return rentPriceTo;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public Long getStaffId() {
        return staffId;
    }

    public List<String> getTypeCode() {
        return typeCode;
    }

    public static class BuildingBuilder {
        private String name;
        private Long floorArea;
        private String district;
        private String ward;
        private String street;
        private Long numberOfBasement;
        private String direction;
        private Long level;
        private Long rentAreaFrom;
        private Long rentAreaTo;
        private Long rentPriceFrom;
        private Long rentPriceTo;
        private String managerName;
        private String managerPhone;
        private Long staffId;
        private List<String> typeCode = new ArrayList<>();

        public BuildingBuilder setName (String name) {
            this.name = name;
            return this;
        }
        public BuildingBuilder setFloorArea (Long floorArea) {
            this.floorArea = floorArea;
            return this;
        }
        public BuildingBuilder setWard (String ward) {
            this.ward = ward;
            return this;
        }
        public BuildingBuilder setStreet (String street) {
            this.street = street;
            return this;
        }
        public BuildingBuilder setDistrict (String district) {
            this.district = district;
            return this;
        }
        public BuildingBuilder setNumberOfBasement (Long numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }
        public BuildingBuilder setTypeCode (List<String> typeCode) {
            this.typeCode = typeCode;
            return this;
        }
        public BuildingBuilder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }
        public BuildingBuilder setManagerPhone (String managerPhone) {
            this.managerPhone = managerPhone;
            return this;
        }
        public BuildingBuilder setRentPriceFrom (Long rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }
        public BuildingBuilder setRentPriceTo (Long rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }
        public BuildingBuilder setRentAreaFrom (Long rentAreaFrom) {
            this.rentAreaFrom = rentAreaFrom;
            return this;
        }
        public BuildingBuilder setRentAreaTo (Long rentAreaTo) {
            this.rentAreaTo = rentAreaTo;
            return this;
        }
        public BuildingBuilder setStaffId (Long staffId) {
            this.staffId = staffId;
            return this;
        }
        public BuildingBuilder setDirection(String direction){
            this.direction = direction;
            return this;
        }
        public BuildingBuilder setLevel(Long level){
            this.level = level;
            return this;
        }
        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }

    }
}
