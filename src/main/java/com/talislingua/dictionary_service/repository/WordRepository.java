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

    @Query("SELECT w FROM Word w WHERE w.azTranslation ILIKE %:word% OR w.talisWord ILIKE %:word%")
    List<Word> findByWordIgnoreCase(@Param("word") String word);

    Optional<Word> findByIsOfDayTrue();

    @Query(value = "SELECT * FROM word OFFSET FLOOR(RANDOM() * (SELECT COUNT(*) FROM word)) LIMIT 1", nativeQuery = true)
    Word findRandomWord();

    @Modifying
    @Transactional
    @Query("UPDATE Word w SET w.isOfDay = false")
    void resetWordOfDay();
}