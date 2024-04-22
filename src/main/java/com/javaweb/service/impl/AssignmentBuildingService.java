package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuilding;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentBuildingService implements IAssignmentBuildingService {
    @Autowired
    private AssignmentBuildingRepository repository;
    @Override
    public void setStaffToBuilding(Long buildingId, List<Long> staffId) {
        int Size = staffId.size();
        repository.deleteByBuildingId(buildingId);
        for(int i = 0; i < Size; i++) {
            AssignmentBuilding assignmentBuilding = new AssignmentBuilding();
            assignmentBuilding.setBuildingId(buildingId);
            assignmentBuilding.setStaffId(staffId.get(i));
            repository.save(assignmentBuilding);
        }
    }
}
