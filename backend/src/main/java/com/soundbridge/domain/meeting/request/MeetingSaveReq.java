package com.soundbridge.domain.meeting.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.soundbridge.domain.meeting.entity.Meeting;
import com.soundbridge.domain.member.entity.Member;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MeetingSaveReq {

    @NotNull(message = "feedbackBoardId cannot be null.")
    private Long feedbackBoardId;
    @NotEmpty(message = "title cannot be empty.")
    private String title;
    @NotNull(message = "helperId cannot be null.")
    private Long applicantId;
    @NotNull(message = "startTime cannot be null")
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime startTime;
    @NotNull(message = "endTime cannot be null")
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime endTime;

    public Meeting toEntity(Member helper, Member applicant, String code) {
        return Meeting.builder()
            .helper(helper)
            .applicant(applicant)
            .code(code)
            .title(this.title)
            .startTime(this.startTime)
            .endTime(this.endTime)
            .build();
    }
}
