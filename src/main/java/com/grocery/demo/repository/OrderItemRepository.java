package com.grocery.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.demo.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
