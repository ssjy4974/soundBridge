package com.soundbridge.domain.record.entity;

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
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RecordState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_state_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "fk_record_state_member_idx"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sentence_id", nullable = false, foreignKey = @ForeignKey(name = "fk_record_state_record_sentence_idx"))
    private RecordSentence recordSentence;

    @Builder
    public RecordState(Member member, RecordSentence recordSentence) {
        this.member = member;
        this.recordSentence = recordSentence;
    }
}
