package com.apple.shop.item;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/test1")
    public String test1(@RequestBody Map<String, Object> body) {
        System.out.println("title: " + body.get("title") + ", price: " + body.get("price"));
        return "redirect:/list";
    }

    @GetMapping("/test2")
    public String test2(@RequestParam Map<String, String> params) {
        System.out.println("title: " + params.get("title") + ", price: " + params.get("price"));
        return "redirect:/list";
    }

    @DeleteMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.delete(id);
        return "redirect:/list";
    }
    

}
