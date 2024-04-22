package com.javaweb.service;

import java.util.List;

public interface IAssignmentBuildingService {
    void setStaffToBuilding(Long customerId, List<Long> staffId);
}
