package com.talislingua.dictionary_service.dto;

import lombok.Data;

@Data
public class WordRequest {
    private String talisWord;
    private String azTranslation;
    private String enTranslation;
}
