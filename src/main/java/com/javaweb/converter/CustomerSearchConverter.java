package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.utils.StatusUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerSearchConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerSearchResponse toCustomerResponse(CustomerEntity customerEntity) {
        CustomerSearchResponse response = modelMapper.map(customerEntity, CustomerSearchResponse.class);
        String status = StatusUtils.checkData(customerEntity.getStatus());
        response.setStatus(status);
        return response;
    }
}
