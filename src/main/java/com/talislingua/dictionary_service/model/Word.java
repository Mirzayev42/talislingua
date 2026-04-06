package com.talislingua.dictionary_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Boolean isOfDay = false;

    @Column(nullable = false)
    private String talisWord;

    @Column(nullable = false)
    private String azTranslation;

    @Column(nullable = false)
    private String enTranslation;

    @Column(length = 1000)
    private String imageUrl;

    private LocalDateTime createdAt = LocalDateTime.now();
}
