package com.cpan252.assignmentOne.cpan252assignmentOne.service;

import com.cpan252.assignmentOne.cpan252assignmentOne.model.Item;
import com.cpan252.assignmentOne.cpan252assignmentOne.model.Brand;
import com.cpan252.assignmentOne.cpan252assignmentOne.repository.InMemoryItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final InMemoryItemRepository itemRepository;

    @Autowired
    public ItemService(InMemoryItemRepository itemRepository) {
        this.itemRepository = itemRepository;

    }

    public void addItem(Item items) {
        // Perform validation before adding the item
        if (items.getPrice() <= 1000) {
            throw new IllegalArgumentException("Price must be more than 1000");
        }

        if (!isValidBrand(items.getBrand())) {
            throw new IllegalArgumentException("Invalid brand");
        }

        if (items.getYearOfCreation() <= 2021) {
            throw new IllegalArgumentException("Year of creation must be more than 2021");
        }

        itemRepository.addItem(items);
    }

    public List<Item> findItemsByBrandAndYear(Brand brand, int year) {
        return null;
    }

    public List<Item> findAll() {
        return null;
    }

    private boolean isValidBrand(Brand brand) {
        for (Brand b : Brand.values()) {
            if (b.equals(brand)) {
                return true;
            }
        }
        return false;
    }


    public List<Item> findByBrand(String brand) {
        return null;
    }

    public List<String> findAllBrands() {
        return null;
    }
}
