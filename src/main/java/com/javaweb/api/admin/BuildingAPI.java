package com.javaweb.api.admin;


import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.impl.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/building")
public class BuildingAPI {

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private AssignmentBuildingService assignmentBuildingService;

    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("/{buildingId}/staffs")
    public ResponseDTO loadStaff(@PathVariable("buildingId") Long buildingId) {
        ResponseDTO dto = buildingService.listStaff(buildingId);
        return dto;
    }

    @GetMapping("/{buildingId}/typeCodes")
    public ResponseDTO typeChecked(@PathVariable("buildingId") Long buildingId) {
        ResponseDTO dto = buildingService.typeChecked(buildingId);
        return dto;
    }

    @PostMapping
    public void createOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) { //thêm hoặc sửa tòa nhà
        buildingService.saveBuilding(buildingDTO);
    }

    @PostMapping("/{id}/{staffId}")
    public void setStaffToBuilding(@PathVariable("id") Long buildingId, @PathVariable("staffId") List<Long> staffId) {
        assignmentBuildingService.setStaffToBuilding(buildingId, staffId); // giao tòa nhà
    }

    @DeleteMapping("/{buildingId}")
    public void deleteBuilding(@PathVariable("buildingId") List<Long> id) {//Xóa tòa nhà
        buildingService.deleteBuilding(id);
    }


}
