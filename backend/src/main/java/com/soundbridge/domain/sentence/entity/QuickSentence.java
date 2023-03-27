package com.soundbridge.domain.sentence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class QuickSentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quick_sentence_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_quick_sentence_category_idx"))
    private Category category;

    @Column(length = 100, nullable = false)
    private String sentence;

    @Column(columnDefinition = "INT UNSIGNED")
    @ColumnDefault("0")
    private int sentenceCount;

    @Builder
    public QuickSentence(Category category, String sentence) {
        this.category = category;
        this.sentence = sentence;
    }

    public void updateQuickSentence(String sentence) {
        this.sentence = sentence;
    }

    public void increaseCount() {
        this.sentenceCount++;
    }
}
