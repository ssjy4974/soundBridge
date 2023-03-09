package com.soundbridge.domain.pronunciation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_word_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @Column(length = 20, nullable = false)
    private String word;

    @Column(length = 20, nullable = false)
    private String guideWord;

    @Builder
    public DailyWord(String word, String guideWord) {
        this.word = word;
        this.guideWord = guideWord;
    }
}
