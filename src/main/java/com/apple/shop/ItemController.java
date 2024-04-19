package com.apple.shop;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/list")
    String list(Model model) {
        var result = itemService.findAll();
        model.addAttribute("items", result);

        return "list.html";
    }

    @GetMapping("/write")
    public String write() {
        return "write.html";
    }

    @PostMapping("/addItem")
    public String addItem(@ModelAttribute Item item) {
        Long itemCount = itemService.count();
        item.setId(itemCount + 1);
        itemService.save(item);
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Optional<Item> result = itemService.findById(id);

        if (result.isPresent()) {
            model.addAttribute("id", result.get().getId());
            model.addAttribute("title", result.get().getTitle());
            model.addAttribute("price", result.get().getPrice());
        }

        return "detail.html";
    }

    @PostMapping("/updateItem")
    public String updateItem(@RequestParam("title") String title, @RequestParam("price") int price) {
        var itemOpt = itemService.findByTitle(title);
        if (itemOpt.isEmpty()) {
            return "error.html";
        }

        Item item = itemOpt.get();
        item.setPrice(price);
        item.setTitle(title);
        itemService.update(item.getId(), item);
        return "redirect:/list";
    }

    @GetMapping("/updateItem")
    public String updateItem() {
        return "updateItem.html";
    }

}
