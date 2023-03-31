package com.soundbridge.domain.voice.controller;

import com.soundbridge.domain.member.response.MemberAccessRes;
import com.soundbridge.domain.voice.request.VoiceDeleteReq;
import com.soundbridge.domain.voice.request.VoiceListConditionReq;
import com.soundbridge.domain.voice.request.VoiceSelectionReq;
import com.soundbridge.domain.voice.service.VoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/voices")
public class VoiceApiController {

    private final VoiceService voiceService;

    @Operation(summary = "목소리 조회", description = "목소리 조회 메소드 입니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "이력 조회 성공"),
        @ApiResponse(responseCode = "400", description = "필수값 누락"),
        @ApiResponse(responseCode = "401", description = "인증 안됨"),
        @ApiResponse(responseCode = "403", description = "권한 부족, 없음"),
        @ApiResponse(responseCode = "404", description = "존재하지않는 유저 정보"),
    })
    @GetMapping()
    public ResponseEntity voiceList(@PageableDefault Pageable pageable,
        @RequestParam(required = false) Long cursorId,
        @ModelAttribute VoiceListConditionReq voiceListConditionReq,
        Authentication authentication) {

        return ResponseEntity.ok(
            voiceService.findAllVoiceWithPaging(pageable, cursorId, voiceListConditionReq, null));
    }

    @Operation(summary = "목소리 조회", description = "목소리 조회 메소드 입니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "이력 조회 성공"),
        @ApiResponse(responseCode = "400", description = "필수값 누락"),
        @ApiResponse(responseCode = "401", description = "인증 안됨"),
        @ApiResponse(responseCode = "403", description = "권한 부족, 없음"),
        @ApiResponse(responseCode = "404", description = "존재하지않는 유저 정보"),
    })
    @GetMapping("/{memberId}")
    public ResponseEntity myVoice(@PathVariable Long memberId,
        Authentication authentication) {

        return ResponseEntity.ok(
            voiceService.findMyVocieByMemberId(memberId));
    }


    @Operation(summary = "목소리 선택", description = "목소리 선택 메소드 입니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "이력 조회 성공"),
        @ApiResponse(responseCode = "400", description = "필수값 누락"),
        @ApiResponse(responseCode = "401", description = "인증 안됨"),
        @ApiResponse(responseCode = "403", description = "권한 부족, 없음"),
        @ApiResponse(responseCode = "404", description = "존재하지않는 유저 정보"),
    })
    @PutMapping("/select")
    public ResponseEntity selectVoice(@RequestBody VoiceSelectionReq voiceSelectionReq,
        Authentication authentication) {
        Long memberId = ((MemberAccessRes) authentication.getPrincipal()).getId();
        log.info("select Voice {}", voiceSelectionReq.toString());
        voiceService.selectByVoiceId(memberId, voiceSelectionReq);

        return ResponseEntity.ok().body(voiceSelectionReq.getVoiceId());
    }

    @Operation(summary = "녹음된 목소리 삭제", description = "녹음 목소리 삭제 메소드 입니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "이력 조회 성공"),
        @ApiResponse(responseCode = "400", description = "필수값 누락"),
        @ApiResponse(responseCode = "401", description = "인증 안됨"),
        @ApiResponse(responseCode = "403", description = "권한 부족, 없음"),
        @ApiResponse(responseCode = "404", description = "존재하지않는 유저 정보"),
    })
    @DeleteMapping("")
    public ResponseEntity deleteVoice(@RequestBody VoiceDeleteReq voiceDeleteReq,
        Authentication authentication) {
        Long memberId = ((MemberAccessRes) authentication.getPrincipal()).getId();

        voiceService.deleteVoiceById(memberId, voiceDeleteReq);

        return ResponseEntity.ok().build();
    }
}
