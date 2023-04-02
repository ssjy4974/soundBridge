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
public class BasicLetter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basic_letter_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @Column(length = 10, nullable = false)
    private String letter;

    @Column(length = 10, nullable = false)
    private String guideLetter;

    @Column(length = 200, nullable = false)
    private String letterImage;

    @Column(length = 200, nullable = false)
    private String guideImage;

    @Column(length = 50)
    private String startTime;

    @Builder
    public BasicLetter(String letter, String guideLetter, String letterImage,
        String guideImage, String startTime) {
        this.letter = letter;
        this.guideLetter = guideLetter;
        this.letterImage = letterImage;
        this.guideImage = guideImage;
        this.startTime = startTime;
    }
}
