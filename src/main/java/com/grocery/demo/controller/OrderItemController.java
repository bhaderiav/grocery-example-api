package com.grocery.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.demo.UserDefinException.ItemOutOfStockException;
import com.grocery.demo.entity.GroceryItem;
import com.grocery.demo.entity.OrderItem;
import com.grocery.demo.services.GroceryItemService;
import com.grocery.demo.services.OrderItemService;

@RestController
@RequestMapping("/api/order")
public class OrderItemController {
	
	
	@Autowired
    private GroceryItemService groceryItemService;

    @GetMapping("/items")
    public ResponseEntity<List<GroceryItem>> getAllAvailableItems() {
        List<GroceryItem> availableItems = groceryItemService.getAvailableItems();
        return new ResponseEntity<>(availableItems, HttpStatus.OK);
    }

    @PostMapping("/OrderItem")
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody List<Long> itemIds) {
        // Retrieve items from IDs
        List<GroceryItem> items = groceryItemService.getItemsByIds(itemIds);

        // Check if items are available
        for (GroceryItem item : items) {
            if (item.getQuantity() <= 0) {
            	throw new ItemOutOfStockException("Item " + item.getName() + " is out of stock");
            }
        }

        // Update inventory levels
        for (GroceryItem item : items) {
            item.setQuantity(item.getQuantity() - 1);
            groceryItemService.saveOrUpdateItem(item);
        }

        // Create and return OrderItem
        OrderItem OrderItem = new OrderItem();
        OrderItem.setItems(items);
        return new ResponseEntity<>(OrderItem, HttpStatus.CREATED);
    }

	}

