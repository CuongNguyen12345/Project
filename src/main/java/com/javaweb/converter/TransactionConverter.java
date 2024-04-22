package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.TransactionSearchResponse;

import com.javaweb.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    public TransactionSearchResponse toTransactionResponse(TransactionEntity transactionEntity) {
        TransactionSearchResponse response = modelMapper.map(transactionEntity, TransactionSearchResponse.class);
        response.setCustomerId(transactionEntity.getCustomerEntity().getId());
        return response;
    }

    public TransactionEntity toTransactionEntity(TransactionDTO transactionDTO) {
        TransactionEntity entity = modelMapper.map(transactionDTO, TransactionEntity.class);
        CustomerEntity customerEntity = customerRepository.findById(transactionDTO.getCustomerId()).get();
        entity.setCustomerEntity(customerEntity);
        entity.setNote(transactionDTO.getTransactionDetail());
        return entity;
    }
}
