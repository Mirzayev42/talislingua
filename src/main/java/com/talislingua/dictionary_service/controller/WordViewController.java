package com.talislingua.dictionary_service.controller;

import com.talislingua.dictionary_service.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
public class WordViewController {
    private final WordService wordService;


    @GetMapping("/dictionary")
    public String showDictionary(Model model) {
        model.addAttribute("words", wordService.getAllWords());
        model.addAttribute("wordOfDay", wordService.getWordOfDay());
        return "dictionary-page";
    }
}
