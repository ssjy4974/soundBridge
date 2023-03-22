package com.soundbridge.domain.pronunciation.entity;

import com.soundbridge.domain.member.entity.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
public class TryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "try_history_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "fk_try_history_member_idx"))
    private Member member;

    @OneToOne
    @JoinColumn(name = "word_member_id", foreignKey = @ForeignKey(name = "fk_try_history_word_member_idx"))
    private WordMember wordMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basic_letter_id", foreignKey = @ForeignKey(name = "fk_try_history_basic_letter_idx"))
    private BasicLetter basicLetter;

    @Column(columnDefinition = "INT UNSIGNED")
    @ColumnDefault("1")
    private Integer tryCount;

    @Column(columnDefinition = "INT UNSIGNED")
    @ColumnDefault("0")
    private Integer successCount;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private PronunciationType type; // BASIC_LETTER 기본발음, DAILY_WORD 일상단어

    @Builder
    public TryHistory(Member member, BasicLetter basicLetter, WordMember wordMember,
        PronunciationType type) {
        this.member = member;
        this.basicLetter = basicLetter;
        this.wordMember = wordMember;
        this.type = type;
    }

    public void increaseTryCount() {
        this.tryCount++;
    }

    public void increaseSuccessCount() {
        this.successCount++;
    }


}
