package com.soundbridge.domain.meeting.controller;

import com.soundbridge.domain.meeting.request.MeetingSaveReq;
import com.soundbridge.domain.meeting.service.MeetingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meetings")
public class MeetingApiController {

    private final MeetingService meetingService;

    @PostMapping
    @Operation(summary = "피드백 상담 생성")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "피드백 상담 생성 성공"),
        @ApiResponse(responseCode = "400", description = "입력 파라미터 오류"),
        @ApiResponse(responseCode = "403", description = "권한 없는 유저"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 유저"),
        @ApiResponse(responseCode = "404", description = "피드백 요청 게시글 삭제 실패")
    })
    public ResponseEntity meetingSave(@Valid @RequestBody MeetingSaveReq req,
        Authentication authentication) {
        meetingService.saveMeeting(req, 1L);
        return ResponseEntity.ok().build();
    }
}
