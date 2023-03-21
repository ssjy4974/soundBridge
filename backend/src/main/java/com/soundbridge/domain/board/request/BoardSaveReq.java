package com.soundbridge.domain.board.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.soundbridge.domain.board.entity.FeedbackBoard;
import com.soundbridge.domain.member.entity.Member;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
public class BoardSaveReq {

    @NotEmpty(message = "title cannot be empty.")
    private String title;
    @NotNull(message = "startTime cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;
    @NotNull(message = "endTIme cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    public FeedbackBoard toEntity(Member member) {
        return FeedbackBoard.builder()
            .title(this.title)
            .member(member)
            .startTime(this.startTime.plusHours(9))
            .endTime(this.endTime.plusHours(9))
            .build();
    }
}
