package com.javaweb.repository;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
//    List<TransactionEntity> findByCodeAndCustomerEntity(String code, CustomerEntity entity);
    List<TransactionEntity> findByCodeLikeAndCustomerEntity(String code, CustomerEntity customerEntity);
    TransactionEntity findByCodeLike(String code);
    List<TransactionEntity> findByCustomerEntity(CustomerEntity customerEntity);
}
