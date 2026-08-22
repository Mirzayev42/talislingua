package com.talislingua.dictionary_service.controller;

import com.talislingua.dictionary_service.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final WordService  wordService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("words", wordService.getAllWords());
        model.addAttribute("wordOfDay", wordService.getWordOfDay());
        return "dictionary-page";
    }
}
