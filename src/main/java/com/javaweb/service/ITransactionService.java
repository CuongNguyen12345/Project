package com.javaweb.service;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.TransactionSearchResponse;

import java.util.List;
public interface ITransactionService {
//    List<TransactionSearchResponse> findByCodeAndCustomerEntity(String code, CustomerEntity entity);
    List<TransactionSearchResponse> findAllByCodeAndCustomerId(String code, Long customerId);
    void saveTransaction(TransactionDTO transactionDTO);
}
