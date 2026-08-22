package com.talislingua.dictionary_service.controller;

import com.talislingua.dictionary_service.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final WordService  wordService;
    @GetMapping("/")

    public String index(Model model) {
        try {
            var words = wordService.getAllWords();
            model.addAttribute("words", words != null ? words : Collections.emptyList());
        } catch (Exception e) {
            model.addAttribute("words", Collections.emptyList());
        }

        try {
            model.addAttribute("wordOfDay", wordService.getWordOfDay());
        } catch (Exception e) {
            model.addAttribute("wordOfDay", null);
        }

        return "dictionary-page";
    }
}
