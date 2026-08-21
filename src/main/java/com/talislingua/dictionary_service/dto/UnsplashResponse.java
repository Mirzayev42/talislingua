package com.talislingua.dictionary_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class UnsplashResponse {
    private List<UnsplashResult> results;

    @Getter
    @Setter
    public static class UnsplashResult {
        private Map<String, String> urls;
    }
}
