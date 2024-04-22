package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.converter.CustomerSearchConverter;
import com.javaweb.entity.AssignmentCustomer;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentCustomerRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentCustomerRepository assignmentCustomerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerSearchConverter converter;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerConverter customerConverter;
    @Override
    public List<CustomerSearchResponse> findAll(CustomerSearchRequest request) {
        List<CustomerEntity> list = customerRepository.findAll(request);
        List<CustomerSearchResponse> result = new ArrayList<>();
        for(CustomerEntity item : list) {
            CustomerSearchResponse response = converter.toCustomerResponse(item);
            result.add(response);
        }
        return result;
    }

    @Override
    public ResponseDTO listStaff(Long customerId) {
        CustomerEntity customer = customerRepository.findById(customerId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssignmentCustomer = customer.getUsers();
        List<Long> staffId = new ArrayList<>();
        for(UserEntity item : staffAssignmentCustomer) {
            staffId.add(item.getId());
        }
        ResponseDTO result = new ResponseDTO();
        List<StaffResponseDTO> dto = new ArrayList<>();
        for(UserEntity item : staffs) {
            StaffResponseDTO staffDTO = new StaffResponseDTO();
            staffDTO.setFullName(item.getFullName());
            staffDTO.setStaffId(item.getId());
            if(staffId.contains(item.getId())) {
                staffDTO.setChecked("checked");
            }
            else {
                staffDTO.setChecked("");
            }
            dto.add(staffDTO);
        }
        result.setData(dto);
        result.setMessage("success");
        return result;
    }

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.converToEntity(customerDTO);
        List<AssignmentCustomer> assignmentCustomers = null;
        List<TransactionEntity> transactionEntities = null;
        if(customerDTO.getId() != null) {
            assignmentCustomers = assignmentCustomerRepository.findByCustomerId(customerDTO.getId());
            transactionEntities = transactionRepository.findByCustomerEntity(customerEntity); // luu lai du lieu truoc kia thay doi thong tin khach hang
            assignmentCustomerRepository.deleteAllByCustomerId(customerDTO.getId());
        }
        customerRepository.save(customerEntity);

//        assignmentCustomerRepository.saveAll(assignmentCustomers);
//        for(AssignmentCustomer item : assignmentCustomers) {
//            assignmentCustomerRepository.save(item);
//        }
//
//        for(TransactionEntity item : transactionEntities) {
//            transactionRepository.save(item);
//        }
        if(customerDTO.getId() != null) {
            assignmentCustomerRepository.saveAll(assignmentCustomers);
            transactionRepository.saveAll(transactionEntities);
        }
    }


    @Override
    @Transactional
    public void deleteCustomer(List<Long> id) {
        for(Long item : id) {
            CustomerEntity entity = customerRepository.findById(item).get();
            entity.setStatus("0");
            customerRepository.save(entity);
        }
    }

    @Override
    public CustomerDTO findById(Long id) {
        CustomerEntity entity = customerRepository.findById(id).get();
        CustomerDTO dto = customerConverter.converToDTO(entity);
        return dto;
    }

    @Override
    public List<CustomerSearchResponse> getCustomers(CustomerSearchRequest request, Pageable pageable) {
        List<CustomerEntity> customerEntities = customerRepository.findAll(request);
        Page<CustomerEntity> entities = new PageImpl<>(customerEntities);
        List<CustomerEntity> list = entities.getContent();
        List<CustomerSearchResponse> results = new ArrayList<>();
        for(CustomerEntity item : list) {
            CustomerSearchResponse response = converter.toCustomerResponse(item);
            results.add(response);
        }
        return results;
    }

    @Override
    public int totalItems(CustomerSearchRequest request) {
        return customerRepository.totalItems(request);
    }
}
