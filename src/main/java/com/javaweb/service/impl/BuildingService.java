package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.converter.BuildingSearchConverter;
import com.javaweb.entity.AreasEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.model.response.typeCodeResponDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;

import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
    @Autowired
    private BuildingSearchConverter buildingSearchConverter;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UploadFileUtils uploadFileUtils;

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder builder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(builder);
        List<BuildingSearchResponse> result = new ArrayList<>();
        for(BuildingEntity item : buildingEntities) {
            BuildingSearchResponse response = buildingSearchConverter.toBuildingResponse(item);
            result.add(response);
        }
        return result;
    }

    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity building = buildingRepository.findById(id).get();
        BuildingDTO dto = buildingSearchConverter.toBuildingDTO(building);
        return dto;
    }

    @Override
    @Transactional
    public void saveBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.converToBuildingEntity(buildingDTO);
        List<String> areas = buildingConverter.convertRentArea(buildingDTO);
        Long buildingID = buildingDTO.getId();
        if(buildingDTO.getId() != null){
            rentAreaRepository.deleteAllByBuildingId(buildingDTO.getId());
            buildingEntity.setImage(buildingDTO.getImage());
        }
        saveThumbnail(buildingDTO, buildingEntity);
        buildingRepository.save(buildingEntity);
        int size = areas.size();
        for(int i = 0; i < size; i++) {
            AreasEntity area = new AreasEntity();
            area.setBuilding(buildingEntity);
            area.setValue(Integer.parseInt(areas.get(i)));
            rentAreaRepository.save(area);
        }
    }

    @Override
    public ResponseDTO typeChecked(Long buildingID) {
        BuildingEntity building = buildingRepository.findById(buildingID).get();
        List<String> typeCode = Arrays.stream(building.getType().split(",")).collect(Collectors.toList());
        String s = "TANG_TRET,NGUYEN_CAN,NOI_THAT";
        List<String> type = Arrays.stream(s.split(",")).collect(Collectors.toList());
        ResponseDTO dto = new ResponseDTO();
        List<typeCodeResponDTO> list = new ArrayList<>();
        for(String it : type) {
            typeCodeResponDTO typeDTO = new typeCodeResponDTO();
            if(it.equals("TANG_TRET")) {
                typeDTO.setFullName("Tầng Trệt");
                typeDTO.setTypeCode(it);
            }
            else if(it.equals("NGUYEN_CAN")) {
                typeDTO.setFullName("Nguyên Căn");
                typeDTO.setTypeCode(it);
            }
            else if(it.equals("NOI_THAT")) {
                typeDTO.setFullName("Nội Thất");
                typeDTO.setTypeCode(it);
            }
            if(typeCode.contains(it)) {
                typeDTO.setChecked("checked");
            }
            else {
                typeDTO.setChecked("");
            }
            list.add(typeDTO);
        }
        dto.setData(list);
        dto.setMessage("success");
        return dto;
    }

    @Override
    public ResponseDTO listStaff(Long buildingID) {
        BuildingEntity building = buildingRepository.findById(buildingID).get();
        List<UserEntity> staff = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssignmentBuilding = building.getUsers(); // lấy những người đang quản lý tòa nhà
        List<Long> staffId = new ArrayList<>();
        for(UserEntity it : staffAssignmentBuilding){
            staffId.add(it.getId());
        }
        List<StaffResponseDTO> list = new ArrayList<>();
        ResponseDTO result = new ResponseDTO();
        for(UserEntity it : staff){
            StaffResponseDTO staffDTO = new StaffResponseDTO();
            staffDTO.setFullName(it.getFullName());
            staffDTO.setStaffId(it.getId());
            if(staffId.contains(it.getId())){
                staffDTO.setChecked("checked");
            }
            else{
                staffDTO.setChecked("");
            }
            list.add(staffDTO);
        }
        result.setData(list);
        result.setMessage("success");
        return result;
    }

    @Override
    public void deleteBuilding(List<Long> buildingId) {
        for(Long id : buildingId) {
            buildingRepository.deleteById(id);
        }
    }

    @Override
    public List<BuildingSearchResponse> getBuildings(BuildingSearchRequest request, Pageable pageable) {
        BuildingSearchBuilder builder = buildingSearchBuilderConverter.toBuildingSearchBuilder(request);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(builder);
        Page<BuildingEntity> buildings = new PageImpl<>(buildingEntities);
        List<BuildingEntity> newEntities = buildings.getContent();
        List<BuildingSearchResponse> results = new ArrayList<>();
        for(BuildingEntity item : newEntities) {
            BuildingSearchResponse response = buildingSearchConverter.toBuildingResponse(item);
            results.add(response);
        }
        return results;
    }

    @Override
    public int totalItems(BuildingSearchRequest request) {
        BuildingSearchBuilder builder = buildingSearchBuilderConverter.toBuildingSearchBuilder(request);
        return buildingRepository.totalItems(builder);
    }

    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("C://home/office" + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }
//    @Override
//    public List<BuildingSearchResponse> findAll() {
//        List<BuildingEntity> buildingEntities = buildingRepository.findAll();
//        List<BuildingSearchResponse> result = new ArrayList<>();
//        for(BuildingEntity item : buildingEntities){
//            BuildingSearchResponse buildingSearchResponse = buildingSearchConverter.toBuildingResponse(item);
//            result.add(buildingSearchResponse);
//        }
//        return result;
//    }
}
