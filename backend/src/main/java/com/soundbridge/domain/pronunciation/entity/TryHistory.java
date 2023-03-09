package com.soundbridge.domain.pronunciation.entity;

import com.soundbridge.domain.member.entity.Member;
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
public class TryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "try_history_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "fk_try_history_member_idx"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_word_id", nullable = false, foreignKey = @ForeignKey(name = "fk_try_history_daily_word_idx"))
    private DailyWord dailyWord;

    @Column(columnDefinition = "INT UNSIGNED")
    @ColumnDefault("1")
    private int tryCount;

    @Column(columnDefinition = "INT UNSIGNED")
    @ColumnDefault("0")
    private int successCount;

    @Builder
    public TryHistory(Member member, DailyWord dailyWord) {
        this.member = member;
        this.dailyWord = dailyWord;
    }
}
