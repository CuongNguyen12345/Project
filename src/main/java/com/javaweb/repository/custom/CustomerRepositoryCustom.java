package com.javaweb.repository.custom;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.CustomerSearchRequest;

import java.util.List;
import java.util.Map;
public interface CustomerRepositoryCustom {
    List<CustomerEntity> findAll(CustomerSearchRequest request);
    int totalItems(CustomerSearchRequest request);
}
