package com.soundbridge.domain.meeting.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeetingDetailRes {

    private Long meetingId;
    private String title;
    private String code;
    private String helperName;
    private String helperProfile;
    private String applicantName;
    private String applicantProfile;
    private int openCheck;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime endTime;

    @QueryProjection
    public MeetingDetailRes(Long meetingId, String title, String code, String helperName,
        String helperProfile, String applicantName, String applicantProfile, int openCheck, LocalDateTime startTime, LocalDateTime endTime) {
        this.meetingId = meetingId;
        this.title = title;
        this.code = code;
        this.helperName = helperName;
        this.helperProfile = helperProfile;
        this.applicantName = applicantName;
        this.applicantProfile = applicantProfile;
        this.openCheck = openCheck;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
