package com.soundbridge.domain.meeting.entity;

import com.soundbridge.domain.member.entity.Member;
import java.time.LocalDateTime;
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
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "helper_id", nullable = false, foreignKey = @ForeignKey(name = "fk_meeting_member_idx1"))
    private Member helper;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable = false, foreignKey = @ForeignKey(name = "fk_meeting_member_idx2"))
    private Member applicant;

    @Column(length = 50, nullable = false)
    private String title;

    @Column
    @ColumnDefault("1")
    private Short openChk;

    @Column(length = 40, nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Builder
    public Meeting(Member helper, Member applicant, String title, String code,
        LocalDateTime startTime, LocalDateTime endTime) {
        this.helper = helper;
        this.applicant = applicant;
        this.title = title;
        this.code = code;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void startMeeting() {
        this.openChk = 0;
    }

    public void doneMeeting() {
        this.openChk = 2;
    }
}
