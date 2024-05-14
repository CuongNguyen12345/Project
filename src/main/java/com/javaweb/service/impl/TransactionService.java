package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.NoteDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.TransactionSearchResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private TransactionConverter converter;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<TransactionSearchResponse> findAllByCodeAndCustomerId(String code, Long customerId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId).get();
        List<TransactionEntity> list = repository.findByCodeLikeAndCustomerEntity(code, customerEntity);
        List<TransactionSearchResponse> result = new ArrayList<>();
        for(TransactionEntity item : list) {
            TransactionSearchResponse response = converter.toTransactionResponse(item);
            result.add(response);
        }
        return result;
    }

    @Override
    public void saveTransaction(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = converter.toTransactionEntity(transactionDTO);
        if(transactionDTO.getId() != null) {
            TransactionEntity oldTransactionEntity = repository.findById(transactionDTO.getId()).get();
            transactionEntity.setCreatedDate(oldTransactionEntity.getCreatedDate());
            transactionEntity.setCreatedBy(oldTransactionEntity.getCreatedBy());
        }
        repository.save(transactionEntity);
    }

    @Override
    public TransactionEntity findById(Long transactionId) {
        return repository.findById(transactionId).get();
    }

    @Override
    public ResponseDTO loadTransaction(Long transactionId) {
        ResponseDTO dto = new ResponseDTO();
        List<NoteDTO> list = new ArrayList<>();
        if(transactionId == 0) {
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setNote(" ");
            list.add(noteDTO);
        }
        else {
            TransactionEntity entity = repository.findById(transactionId).get();
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setNote(entity.getNote());
            list.add(noteDTO);
        }

        dto.setData(list);
        dto.setMessage("success");
        return dto;
    }
}
