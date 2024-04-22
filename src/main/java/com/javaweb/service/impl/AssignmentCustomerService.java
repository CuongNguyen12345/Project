package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentCustomer;
import com.javaweb.repository.AssignmentCustomerRepository;
import com.javaweb.service.IAssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentCustomerService implements IAssignmentCustomerService {

    @Autowired
    private AssignmentCustomerRepository repository;

    @Override
    public void setStaffForCustomer(Long customerId, List<Long> staffId) {
        int size = staffId.size();
        repository.deleteByCustomerId(customerId);
        for(int i = 0; i < size; i++) {
            AssignmentCustomer assignmentCustomer = new AssignmentCustomer();
            assignmentCustomer.setCustomerId(customerId);
            assignmentCustomer.setStaffId(staffId.get(i));
            repository.save(assignmentCustomer);
        }
    }
}
