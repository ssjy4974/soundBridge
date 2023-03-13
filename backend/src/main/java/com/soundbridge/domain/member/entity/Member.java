package com.soundbridge.domain.member.entity;

import com.soundbridge.domain.voice.entity.Voice;
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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@DynamicInsert
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voice_id", foreignKey = @ForeignKey(name = "fk_member_voice_idx"))
    private Voice voice;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 150)
    @ColumnDefault("'default.png'")
    private String profile;

    @Column(nullable = false)
    private int age;

    @Column(length = 10, nullable = false)
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Role role; // HELPER 봉사자, APPLICANT 지원자

    @Column(length = 30, nullable = false)
    private String nickname;

    @Builder
    public Member(String email, String profile, int age, String gender, String nickname) {
        this.email = email;
        this.profile = profile;
        this.age = age;
        this.gender = gender;
        this.nickname = nickname;
    }
}
