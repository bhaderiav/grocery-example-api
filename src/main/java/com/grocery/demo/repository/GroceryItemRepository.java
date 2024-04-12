package com.grocery.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.demo.entity.GroceryItem;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
}
