package com.cpan252.assignmentOne.cpan252assignmentOne;

import com.cpan252.assignmentOne.cpan252assignmentOne.model.Brand;
import com.cpan252.assignmentOne.cpan252assignmentOne.model.Item;
import com.cpan252.assignmentOne.cpan252assignmentOne.repository.ItemJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final ItemJpaRepository itemRepository;

    @Autowired
    public DataInitializer(ItemJpaRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) {
        Item item1 = new Item("Shirt", Brand.BALENCIAGA, 2022, 1500);
        Item item2 = new Item("Jeans", Brand.DIOR, 2022, 1200);
        Item item3 = new Item("shoes",Brand.STONE_ISLAND, 2022, 1300);
        Item item4 = new Item("hat",Brand.STONE_ISLAND, 2022, 1100);
        Item item5 = new Item("tie",Brand.DIOR, 2022, 1400);
        Item item6 = new Item("jacket",Brand.BALENCIAGA, 2022, 2000);

        itemRepository.save(item2);
        itemRepository.save(item1);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);
        itemRepository.save(item6);


    }
}
