package com.talislingua.dictionary_service.controller;

import com.talislingua.dictionary_service.dto.WordRequest;
import com.talislingua.dictionary_service.model.Word;
import com.talislingua.dictionary_service.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/words")
@RequiredArgsConstructor
public class WordController {
    private final WordService wordService;

    @PostMapping
    public ResponseEntity<Word> addWord(@RequestBody WordRequest wordRequest) {
        return ResponseEntity.ok(wordService.createWord(wordRequest));
    }

    @GetMapping
    public ResponseEntity<List<Word>> getAll() {
        return ResponseEntity.ok(wordService.getAllWords());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Word>> searchWord(@RequestParam String query) {
        // Əgər sorğu boşdursa, xəta verməsin, boş siyahı qaytarsın
        if (query == null || query.trim().isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(wordService.searchWord(query));
    }

    /**
     * Günün sözünü gətirmək üçün endpoint.
     * GET /api/v1/words/day
     */
    @GetMapping("/day")
    public ResponseEntity<Word> getWordOfDay() {
        return ResponseEntity.ok(wordService.getWordOfDay());
    }
}