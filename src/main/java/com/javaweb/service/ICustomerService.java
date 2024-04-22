package com.javaweb.service;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ICustomerService {
    List<CustomerSearchResponse> findAll(CustomerSearchRequest request);
    ResponseDTO listStaff(Long customerId);

    void saveCustomer(CustomerDTO customerDTO);

    void deleteCustomer(List<Long> id);

    CustomerDTO findById(Long id);

    List<CustomerSearchResponse> getCustomers(CustomerSearchRequest request, Pageable pageable);

    int totalItems(CustomerSearchRequest request);
}
