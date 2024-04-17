package com.apple.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardRepository boardRepository;
    
    @GetMapping("/board")
    String board(Model model) {
        var boards = boardRepository.findAll();
        model.addAttribute("boards", boards);

        return "board.html";
    }
}
