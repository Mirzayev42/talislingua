package com.talislingua.dictionary_service.service;

import com.talislingua.dictionary_service.dto.WordRequest;
import com.talislingua.dictionary_service.mapper.WordMapper;
import com.talislingua.dictionary_service.model.Word;
import com.talislingua.dictionary_service.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class WordService {

    private final WordRepository wordRepository;
    private final WordMapper wordMapper;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${unsplash.access-key}")
    private String unsplashAccessKey;

    public Word createWord(WordRequest wordRequest) {
        log.info("Yeni söz əlavə edilir: {} ({})",
                wordRequest.getAzTranslation(),
                wordRequest.getEnTranslation());

        Word word = wordMapper.toEntity(wordRequest);
        String queryWord = wordRequest.getEnTranslation();
        String azWord = wordRequest.getAzTranslation();

        try {
            String url = String.format("https://api.unsplash.com/search/photos?query=%s&client_id=%s&per_page=1",
                    queryWord, unsplashAccessKey);

            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            if (response != null && response.get("results") != null) {
                List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");

                if (!results.isEmpty()) {
                    Map<String, String> urls = (Map<String, String>) results.get(0).get("urls");
                    word.setImageUrl(urls.get("regular"));
                } else {
                    word.setImageUrl("https://via.placeholder.com/1024?text=" + azWord);
                }
            }
        } catch (Exception e) {
            log.error("Unsplash xətası: {}", e.getMessage());
            word.setImageUrl("https://via.placeholder.com/1024?text=" + azWord);
        }

        return wordRepository.save(word);
    }

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public List<Word> searchWord(String query) {
        log.info("Axtarış sorğusu: {}", query);
        return wordRepository.findByWordIgnoreCase(query);
    }

    // --- GÜNÜN SÖZÜ ÖZƏLLİYİ ---

    /**
     * Hər gecə saat 00:00-da işləyir.
     * Əvvəlcə köhnə günün sözünü sıfırlayır, sonra təsadüfi birini seçir.
     */
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void updateWordOfDay() {
        log.info("Günün sözü yenilənməyə başladı...");

        wordRepository.resetWordOfDay();

        Word randomWord = wordRepository.findRandomWord();

        if (randomWord != null) {
            randomWord.setIsOfDay(true);
            wordRepository.save(randomWord);
            log.info("Yeni günün sözü təyin edildi: {}", randomWord.getTalisWord());
        }
    }

    /**
     * Ana səhifə üçün günün sözünü gətirir.
     */
    public Word getWordOfDay() {
        return wordRepository.findByIsOfDayTrue()
                .orElseGet(() -> {
                    log.warn("Günün sözü tapılmadı, təsadüfi biri təyin edilir.");
                    Word firstWord = wordRepository.findRandomWord();
                    if (firstWord != null) {
                        firstWord.setIsOfDay(true);
                        wordRepository.save(firstWord);
                    }
                    return firstWord;
                });
    }
}