package com.talislingua.dictionary_service.repository;

import com.talislingua.dictionary_service.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    // 1. Axtarış üçün (Böyük-kiçik hərf fərqi olmadan)
    @Query("SELECT w FROM Word w WHERE w.azTranslation ILIKE %:word% OR w.talisWord ILIKE %:word%")
    List<Word> findByWordIgnoreCase(@Param("word") String word);

    // 2. Hazırkı günün sözünü tapmaq üçün
    Optional<Word> findByIsOfDayTrue();

    // 3. Bazadan təsadüfi bir söz gətirmək üçün (PostgreSQL üçün native query)
    @Query(value = "SELECT * FROM word ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Word findRandomWord();

    // 4. Bütün isOfDay sahələrini false etmək (Hər yeni gün seçiləndə köhnəni sıfırlamaq üçün)
    @Modifying
    @Transactional
    @Query("UPDATE Word w SET w.isOfDay = false")
    void resetWordOfDay();
}