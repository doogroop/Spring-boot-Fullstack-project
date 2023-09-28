package com.cpan252.assignmentOne.cpan252assignmentOne.repository;

import com.cpan252.assignmentOne.cpan252assignmentOne.model.Brand;
import com.cpan252.assignmentOne.cpan252assignmentOne.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryItemRepository {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    // Method to find all items
    List<Item> findAll() {
        return items;
    }

    // Method to find items by brand and yearOfCreation
    public List<Item> findByBrandAndYearOfCreation(Brand brand, int yearOfCreation) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getBrand() == brand && item.getYearOfCreation() == yearOfCreation) {
                result.add(item);
            }
        }
        return result;
    }


}