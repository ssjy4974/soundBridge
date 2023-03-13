package com.soundbridge.domain.pronunciation.controller;

import com.soundbridge.domain.pronunciation.response.BasicLetterRes;
import com.soundbridge.domain.pronunciation.service.BasicLetterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PronunciationApiController {

    private final BasicLetterService basicLetterService;

    @GetMapping("/basic-letters")
    @Operation(summary = "기본 발음 전체 조회")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "기본 발음 전체 조회 성공")
    })
    public ResponseEntity<List<BasicLetterRes>> basicLetterList(Authentication authentication) {
        return ResponseEntity.ok(basicLetterService.findAllBasicLetters(1L));
    }


}
