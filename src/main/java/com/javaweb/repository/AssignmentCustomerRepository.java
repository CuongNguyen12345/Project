package com.javaweb.repository;

import com.javaweb.entity.AssignmentCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AssignmentCustomerRepository extends JpaRepository<AssignmentCustomer, Long> {
    @Transactional
    void deleteByCustomerId(Long customerId);

    List<AssignmentCustomer> findByCustomerId(Long customerId);

    @Transactional
    void deleteAllByCustomerId(Long customerId);
}
