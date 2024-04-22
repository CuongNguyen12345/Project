package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.enums.districtCode;
import com.javaweb.enums.buildingType;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.MessageUtils;


import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Security;
import java.util.List;
import java.util.Map;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private IBuildingService iBuildingService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private MessageUtils messageUtils;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchRequest buildingSearchRequest, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        mav.addObject("listStaff", iUserService.getStaffList());
        mav.addObject("modelSearch", buildingSearchRequest);
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")) {
            Long staffId = SecurityUtils.getPrincipal().getId();
            buildingSearchRequest.setStaffId(staffId);
            mav.addObject("buildings", iBuildingService.findAll(buildingSearchRequest));
        }
        else {
            mav.addObject("buildings", iBuildingService.findAll(buildingSearchRequest));
        }
        BuildingSearchResponse model = new BuildingSearchResponse();
        DisplayTagUtils.of(request, model);
        List<BuildingSearchResponse> buildings = iBuildingService.getBuildings(buildingSearchRequest, PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
        model.setListResult(buildings);
        model.setTotalItems(iBuildingService.totalItems(buildingSearchRequest));
        mav.addObject(SystemConstant.MODEL, model);
        initMessageResponse(mav, request);
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

    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView createBuilding(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        return mav;
    }

    @RequestMapping(value = "/admin/building-edit-{id}", method = RequestMethod.GET)
    public ModelAndView updateBuilding(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districts", districtCode.type());
        mav.addObject("typeCodes", buildingType.type());
        mav.addObject("buildingEdit", iBuildingService.findById(id));
        return mav;
    }


}
