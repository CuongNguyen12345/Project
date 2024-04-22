package com.javaweb.service;

import java.util.List;

public interface IAssignmentCustomerService {
    void setStaffForCustomer(Long customerId, List<Long> staffId);


}
