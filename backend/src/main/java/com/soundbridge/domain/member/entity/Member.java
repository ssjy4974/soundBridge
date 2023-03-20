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

    private int age;

    @Column(length = 10)
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Role role; // HELPER 봉사자, APPLICANT 지원자

    @Column(length = 30, nullable = false)
    private String nickname;

    private int delFlag;


    @Builder
    public Member(String email, String profile, int age, String gender, String nickname,
        int delFlag) {
        this.email = email;
        this.profile = profile;
        this.age = age;
        this.gender = gender;
        this.nickname = nickname;
        this.delFlag = delFlag;
    }

    public void modifyNickname(String nickname) {
        this.nickname = nickname;
    }

    public void modifyProfile(String profile) {
        this.profile = profile;
    }

    public void deleteMember() {
        this.delFlag = 1;
    }

    public void saveAddInfo(int age, String gender, Role role) {
        this.age = age;
        this.gender = gender;
        this.role = role;
    }

    public void selectVoice(Voice voice) {
        this.voice = voice;
    }
}
