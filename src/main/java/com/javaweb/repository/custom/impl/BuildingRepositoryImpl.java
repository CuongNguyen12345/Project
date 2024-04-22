package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    public void querySimple(BuildingSearchBuilder builder, StringBuilder where) {
        // Java Reflection
        try {
            Field[] field = BuildingSearchBuilder.class.getDeclaredFields();
            for(Field item : field) {
                item.setAccessible(true);
                String fieldName = item.getName(); //Key
                if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("rentArea") && !fieldName.startsWith("rentPrice")) {
                    Object value = item.get(builder);
                    if(value != null) {
                        if(item.getType().getName().equals("java.lang.String") && !value.equals("")) {
                            where.append(" And b." + fieldName.toLowerCase() + " like '%" + value + "%'");
                        }
                        else if(item.getType().getName().equals("java.lang.Integer") && !value.equals("")){
                            where.append(" AND b." + fieldName.toLowerCase() + " = " + value);
                        }
                        else if(item.getType().getName().equals("java.lang.Long") && !value.equals("")){
                            where.append(" AND b." + fieldName.toLowerCase() + " = " + value);
                        }
                    }
                }
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }


    }

    public void querySpecial(BuildingSearchBuilder builder, StringBuilder where) {
        Long rentPriceFrom = builder.getRentPriceFrom();
        Long rentPriceTo = builder.getRentPriceTo();
        Long rentAreaFrom = builder.getRentAreaFrom();
        Long rentAreaTo = builder.getRentAreaTo();
        Long staffId = builder.getStaffId();
        List<String> typeCode = builder.getTypeCode();

        if(rentPriceFrom != null) {
            where.append(" AND b.rentprice >= " + rentPriceFrom);
        }

        if(rentPriceTo != null) {
            where.append(" AND b.rentprice <= " + rentPriceTo);
        }

        if(rentAreaFrom != null || rentAreaTo != null) {
            where.append(" AND EXISTS (SELECT * FROM rentarea r where b.id = r.buildingid");
            if(rentAreaFrom != null) {
                where.append(" AND r.value >= " + rentAreaFrom);
            }
            if(rentAreaTo != null) {
                where.append(" AND r.value <= " + rentAreaTo);
            }
            where.append(")");
        }

        if(staffId != null) {
            where.append(" AND EXISTS (SELECT * FROM assignmentbuilding assign where b.id = assign.buildingid "
                    + "and assign.staffid = " + staffId + ")");
        }

        if(typeCode != null) {
            if(typeCode.size() != 0){
                where.append(" AND (");
                String query = typeCode.stream().map(it -> "b.type LIKE '%" + it + "%'").collect(Collectors.joining(" OR "));
                where.append(query);
                where.append(")");
            }
        }
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder builder) {
        String sql = buildQuery(builder);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public int totalItems(BuildingSearchBuilder builder) {
        String sql = buildQuery(builder);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList().size();
    }

//    @Override
//    public List<BuildingEntity> getAllBuildings(Pageable pageable) {
//        StringBuilder sql = new StringBuilder(buildQueryFilter())
//                .append(" LIMIT ").append(pageable.getPageSize()).append("\n")
//                .append(" OFFSET ").append(pageable.getOffset());
//        System.out.println("Final query: " + sql.toString());
//        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
//        return query.getResultList();
//    }

//    private String buildQueryFilter() {
//        String sql = "SELECT * FROM building b";
//        return sql;
//    }

    private String buildQuery(BuildingSearchBuilder builder) {
        StringBuilder sql = new StringBuilder("SELECT * From building as b ");
        sql.append(SystemConstant.ONE_EQUAL_ONE);
        querySpecial(builder, sql);
        querySimple(builder, sql);
        sql.append("\n GROUP BY b.id ");
        return sql.toString();
    }
}
