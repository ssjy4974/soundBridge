package com.soundbridge.domain.meeting.response;

import com.querydsl.core.annotations.QueryProjection;
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

    @QueryProjection
    public MeetingDetailRes(Long meetingId, String title, String code, String helperName,
        String helperProfile, String applicantName, String applicantProfile) {
        this.meetingId = meetingId;
        this.title = title;
        this.code = code;
        this.helperName = helperName;
        this.helperProfile = helperProfile;
        this.applicantName = applicantName;
        this.applicantProfile = applicantProfile;
    }
}
