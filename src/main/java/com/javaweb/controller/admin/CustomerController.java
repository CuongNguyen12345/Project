package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.enums.TransactionType;
import com.javaweb.enums.StatusType;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.TransactionSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private MessageUtils messageUtils;

    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView customerList(@ModelAttribute("modelSearch")CustomerSearchRequest request, HttpServletRequest servletRequest) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("listStaff", userService.getStaffList());
        mav.addObject("modelSearch", request);
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")) {
            Long staffId = SecurityUtils.getPrincipal().getId();
            request.setStaffId(staffId);
            mav.addObject("customers", customerService.findAll(request));
        }
        else {
            mav.addObject("customers", customerService.findAll(request));
        }
        CustomerSearchResponse model = new CustomerSearchResponse();
        DisplayTagUtils.of(servletRequest, model);
        List<CustomerSearchResponse> customers = customerService.getCustomers(request, PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
        model.setListResult(customers);
        model.setTotalItems(customerService.totalItems(request));
        mav.addObject(SystemConstant.MODEL, model);
        initMessageResponse(mav, servletRequest);
        return mav;
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtils.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }

    @RequestMapping(value = "/admin/customer-edit", method = RequestMethod.GET)
    public ModelAndView addCustomer(@ModelAttribute("customerEdit") CustomerDTO customerDTO) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("transactionType", TransactionType.transactionType());
        mav.addObject("status", StatusType.statusType());
        return mav;
    }

    @RequestMapping(value = "/admin/customer-edit-{id}", method = RequestMethod.GET)
    public ModelAndView updateCustomer(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("customerEdit", customerService.findById(id));
        mav.addObject("transactionType", TransactionType.transactionType());
        mav.addObject("status", StatusType.statusType());
        List<TransactionSearchResponse> responsesCSKH = transactionService.findAllByCodeAndCustomerId("CSKH", id);
        mav.addObject("transactionCSKH", responsesCSKH);
        List<TransactionSearchResponse> responsesDDX = transactionService.findAllByCodeAndCustomerId("DDX", id);
        mav.addObject("transactionDDX", responsesDDX);
        return mav;
    }
}
