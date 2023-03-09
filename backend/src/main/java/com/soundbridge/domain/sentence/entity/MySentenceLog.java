package com.soundbridge.domain.sentence.entity;

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
public class MySentenceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_sentence_log_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "fk_my_sentence_log_member_idx"))
    private Member member;

    @Column(length = 200, nullable = false)
    private String sentenceLog;

    @Column(columnDefinition = "INT UNSIGNED")
    @ColumnDefault("1")
    private int count;

    @Builder
    public MySentenceLog(Member member, String sentenceLog) {
        this.member = member;
        this.sentenceLog = sentenceLog;
    }
}
