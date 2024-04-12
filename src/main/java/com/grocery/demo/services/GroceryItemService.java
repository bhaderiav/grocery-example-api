package com.grocery.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.demo.entity.GroceryItem;
import com.grocery.demo.repository.GroceryItemRepository;

@Service
public class GroceryItemService {
    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public List<GroceryItem> getAllItems() {
        return groceryItemRepository.findAll();
    }

    public Optional<GroceryItem> getItemById(Long id) {
        return groceryItemRepository.findById(id);
    }

    public GroceryItem saveOrUpdateItem(GroceryItem item) {
        return groceryItemRepository.save(item);
    }

    public void deleteItemById(Long id) {
        groceryItemRepository.deleteById(id);
    }
    
    public List<GroceryItem> getAvailableItems() {
        List<GroceryItem> allItems = groceryItemRepository.findAll();
        return allItems.stream()
                .filter(item -> item.getQuantity() > 0)
                .collect(Collectors.toList());
    }
    public List<GroceryItem> getItemsByIds(List<Long> itemIds) {
        return groceryItemRepository.findAllById(itemIds);
    }

}
