package com.javaweb.api.admin;

import java.util.*;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IAssignmentCustomerService;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerAPI {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IAssignmentCustomerService assignmentCustomerService;

    @Autowired
    private ITransactionService transactionService;

    @GetMapping("/{customerId}/staffs")
    public ResponseDTO loadStaff(@PathVariable("customerId") Long customerID) {
        ResponseDTO dto = customerService.listStaff(customerID);
        return dto;
    }

    @PostMapping
    public void addOrUpdateCustomer(@RequestBody CustomerDTO customerDTO) {
//        System.out.println("OK");
        customerService.saveCustomer(customerDTO);
    }

    @PostMapping("/{id}/{staffId}")
    public void setStaffForCustomer(@PathVariable("id") Long id, @PathVariable("staffId") List<Long> staffId) {
        assignmentCustomerService.setStaffForCustomer(id, staffId);
    }

    @PostMapping("/transaction")
    public void addOrUpdateTransaction(@RequestBody TransactionDTO transactionDTO) {
        transactionService.saveTransaction(transactionDTO);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") List<Long> id) {
        customerService.deleteCustomer(id);
    }
}
