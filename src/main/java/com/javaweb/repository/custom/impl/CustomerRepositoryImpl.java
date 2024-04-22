package com.javaweb.repository.custom.impl;

import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.utils.DataUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    public void querySimple(StringBuilder where, CustomerSearchRequest request) {
        String fullName = request.getFullName();
        if(DataUtils.checkData(fullName)) {
            where.append(" and c.fullname like '%" + fullName + "%'");
        }
        String phone = request.getPhoneNumber();
        if(DataUtils.checkData(phone)) {
            where.append(" and c.phone like '%" + phone + "%'");
        }
        String email = request.getEmail();
        if(DataUtils.checkData(email)) {
            where.append(" and c.email like '%" + email + "%'");
        }
        Long staffId = request.getStaffId();
        if(staffId != null) {
            where.append(" and exists (SELECT * FROM assignmentcustomer assign where c.id = assign.customerid "
            + "and assign.staffid = " + staffId + ")");
        }
    }
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomerEntity> findAll(CustomerSearchRequest request) {
        String sql = buildQuery(request);
        Query query = entityManager.createNativeQuery(sql, CustomerEntity.class);
        return query.getResultList();
    }

    @Override
    public int totalItems(CustomerSearchRequest request) {
        String sql = buildQuery(request);
        Query query = entityManager.createNativeQuery(sql, CustomerEntity.class);
        return query.getResultList().size();
    }

    private String buildQuery(CustomerSearchRequest request) {
        StringBuilder sql = new StringBuilder("SELECT * FROM customer as c ");
        sql.append(SystemConstant.ONE_EQUAL_ONE);
        sql.append(" and c.status != '0'");
        querySimple(sql, request);
        return sql.toString();
    }
}
