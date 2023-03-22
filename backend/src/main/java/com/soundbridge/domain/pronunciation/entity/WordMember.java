package com.soundbridge.domain.pronunciation.entity;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.voice.entity.Feature;
import com.soundbridge.domain.voice.entity.Voice;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
public class WordMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_member_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_word_id", nullable = false, foreignKey = @ForeignKey(name = "fk_word_member_word_idx"))
    private DailyWord dailyWord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "fk_word_member_member_idx"))
    private Member member;

}
