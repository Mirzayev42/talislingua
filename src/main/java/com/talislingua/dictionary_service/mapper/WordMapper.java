package com.talislingua.dictionary_service.mapper;

import com.talislingua.dictionary_service.dto.WordRequest;
import com.talislingua.dictionary_service.model.Word;
import org.springframework.stereotype.Component;

@Component
public class WordMapper {

    public Word toEntity(WordRequest request) {
        if (request == null) return null;

        Word word = new Word();
        word.setTalisWord(request.getTalisWord());
        word.setAzTranslation(request.getAzTranslation());
        word.setEnTranslation(request.getEnTranslation());
        return word;
    }
}
