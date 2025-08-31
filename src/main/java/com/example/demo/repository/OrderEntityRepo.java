package com.example.demo.repository;

import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderEntityRepo extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findByUserIdAndStatusStatusId(int userId, int statusId);
    List<OrderEntity> findByUserId(int userId);
}
