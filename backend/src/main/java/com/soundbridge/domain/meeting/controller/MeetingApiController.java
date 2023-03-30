package com.soundbridge.domain.meeting.controller;

import com.soundbridge.domain.meeting.request.MeetingSaveReq;
import com.soundbridge.domain.meeting.response.MeetingDetailRes;
import com.soundbridge.domain.meeting.service.MeetingRoomService;
import com.soundbridge.domain.meeting.service.MeetingService;
import com.soundbridge.domain.member.response.MemberAccessRes;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meetings")
public class MeetingApiController {

    private final MeetingService meetingService;
    private final MeetingRoomService meetingRoomService;

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
        Long memberId = ((MemberAccessRes) authentication.getPrincipal()).getId();
        meetingService.saveMeeting(req, memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "상담 페이징 조회")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "상담 페이징 성공"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 유저"),
    })
    public ResponseEntity<Slice<MeetingDetailRes>> meetingList(
        @PageableDefault(size = 5) Pageable pageable,
        @RequestParam(required = false) Long cursorId,
        Authentication authentication) {
        Long memberId = ((MemberAccessRes) authentication.getPrincipal()).getId();
        return ResponseEntity.ok(meetingService.findAllWithPaging(pageable, cursorId, memberId));
    }

    @GetMapping("/{meetingId}")
    @Operation(summary = "상담 상세 조회")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "상담 상세 성공"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 유저"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 상담")
    })
    public ResponseEntity<MeetingDetailRes> meetingDetails(
        @PathVariable Long meetingId,
        Authentication authentication) {
        return ResponseEntity.ok(meetingService.findMeeting(meetingId));
    }

    @PostMapping("/rooms/{meetingId}")
    @Operation(summary = "미팅 룸 생성")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "미팅 룸 생성 성공"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 유저"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 방")
    })
    public ResponseEntity meetingRoomCreate(
        @PathVariable Long meetingId,
        Authentication authentication) throws OpenViduJavaClientException, OpenViduHttpException {
        meetingRoomService.createRoom(meetingId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rooms/{meetingId}")
    @Operation(summary = "미팅 룸 참가")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "미팅 룸 참가 성공"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 유저"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 방")
    })
    public ResponseEntity<String> meetingRoomJoin(
        @PathVariable Long meetingId,
        Authentication authentication) throws OpenViduJavaClientException, OpenViduHttpException {
        final String token = meetingRoomService.joinRoom(meetingId);
        return ResponseEntity.ok(token);
    }

    @PutMapping("/rooms/{meetingId}")
    @Operation(summary = "상담 종료")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "상담 종료"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 유저"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 방")
    })
    public ResponseEntity meetingRoomDone(
        @PathVariable Long meetingId,
        Authentication authentication) {
        meetingRoomService.doneMeeting(meetingId);
        return ResponseEntity.ok().build();
    }
}
