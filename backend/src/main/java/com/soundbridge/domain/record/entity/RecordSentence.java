package com.soundbridge.domain.record.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) //누락 방지
@Getter
public class RecordSentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sentence_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @Column(length = 200, nullable = false)
    private String content;

    @Builder
    public RecordSentence(String content) {
        this.content = content;
    }
}
