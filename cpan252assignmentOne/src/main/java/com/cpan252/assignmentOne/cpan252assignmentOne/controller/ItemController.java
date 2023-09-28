package com.cpan252.assignmentOne.cpan252assignmentOne.controller;

import com.cpan252.assignmentOne.cpan252assignmentOne.model.Brand;
import com.cpan252.assignmentOne.cpan252assignmentOne.model.Item;
import com.cpan252.assignmentOne.cpan252assignmentOne.model.User;
import com.cpan252.assignmentOne.cpan252assignmentOne.repository.ItemJpaRepository;
import com.cpan252.assignmentOne.cpan252assignmentOne.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Controller
@Valid
@Slf4j
public class ItemController {
    private final ItemService itemService;
    private final ItemJpaRepository itemRepository;

    @Autowired
    public ItemController(ItemService itemService, ItemJpaRepository itemRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/add")
    public String showAddItemForm(Model model) {
        model.addAttribute("items", new Item());
        return "add_item";
    }

    @PostMapping("/add")
    public String addItemToStock(@ModelAttribute Item items) {
        itemService.addItem(items);
        itemRepository.save(items);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String showItemList(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               @RequestParam(defaultValue = "name") String sort,
                               Model model) {
        Page<Item> item = itemRepository.findAll(PageRequest.of(page, size, Sort.by(sort)));
        model.addAttribute("items", item);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", item.getTotalPages());
        model.addAttribute("sortField", sort);
        return "item_list";
    }

    @GetMapping("/listByBrandAndYear")
    public String listByBrandAndYear(@RequestParam(name = "brand", required = false) String brand, Model model) {
        List<Item> items;

        items = itemService.findByBrand(brand);

        List<String> brands = itemService.findAllBrands();
        model.addAttribute("items", items);
        model.addAttribute("brands", brands);

        return "item_list";
    }

    @PostMapping("/deleteAllItems")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String processFightersDeletion(@AuthenticationPrincipal User user) {
        user.getAuthorities();
        itemRepository.deleteAll();
        return "redirect:/list";
    }

    @GetMapping("/adminPage")
    public String adminPage(@AuthenticationPrincipal User user) {
        user.getAuthorities();
        return "clothing_management";
    }
    @PostMapping("/adminPage")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String customeAdminPage(@AuthenticationPrincipal User user) {
        user.getAuthorities();
        return "redirect:/adminPage";
    }



}
