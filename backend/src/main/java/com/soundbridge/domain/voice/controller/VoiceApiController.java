package com.soundbridge.domain.voice.controller;

import com.soundbridge.domain.member.response.MemberAccessRes;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        @RequestParam(required = false) Long cursorId, @ModelAttribute VoiceListConditionReq voiceListConditionReq, Authentication authentication) {

//        Long memberId = ((MemberAccessRes)authentication.getPrincipal()).getId();

        return ResponseEntity.ok(voiceService.findAllVoiceWithPaging(pageable, cursorId, voiceListConditionReq, 1L));
    }

    @Operation(summary = "다음에 진행할 녹음 조회", description = "다음에 진행할 녹음 조회 메소드 입니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "이력 조회 성공"),
        @ApiResponse(responseCode = "400", description = "필수값 누락"),
        @ApiResponse(responseCode = "401", description = "인증 안됨"),
        @ApiResponse(responseCode = "403", description = "권한 부족, 없음"),
        @ApiResponse(responseCode = "404", description = "존재하지않는 유저 정보"),
    })
    @PutMapping("/select")
    public ResponseEntity selectVoice(@RequestBody VoiceSelectionReq voiceSelectionReq, Authentication authentication){
//        Long memberId = ((MemberAccessRes) authentication.getPrincipal()).getId();

        voiceService.selectByVoiceId(1L, voiceSelectionReq);

        return ResponseEntity.ok().build();
    }
}
