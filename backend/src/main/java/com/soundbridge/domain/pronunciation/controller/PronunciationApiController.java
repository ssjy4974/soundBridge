package com.soundbridge.domain.pronunciation.controller;

import com.soundbridge.domain.pronunciation.response.BasicLetterRes;
import com.soundbridge.domain.pronunciation.response.DailyWordRes;
import com.soundbridge.domain.pronunciation.service.BasicLetterService;
import com.soundbridge.domain.pronunciation.service.DailyWordService;
import com.soundbridge.domain.pronunciation.service.TryHistoryService;
import com.soundbridge.domain.pronunciation.service.WordMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PronunciationApiController {

    private final BasicLetterService basicLetterService;
    private final WordMemberService wordMemberService;
    private final DailyWordService dailyWordService;
    private final TryHistoryService tryHistoryService;

    @GetMapping("/basic-letters")
    @Operation(summary = "기본 발음 전체 조회")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "기본 발음 전체 조회 성공")
    })
    public ResponseEntity<List<BasicLetterRes>> basicLetterList(Authentication authentication) {
        return ResponseEntity.ok(basicLetterService.findAllBasicLetters(1L));
    }

    @GetMapping("/daily-words")
    @Operation(summary = "일상 단어 전체 조회")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "일상 단어 전체 조회 성공"),
    })
    public ResponseEntity<List<DailyWordRes>> dailyWordList(Authentication authentication) {

        return ResponseEntity.ok(wordMemberService.findAllDailyWords(1L));
    }

    @PostMapping("/try-histories/basic-letters/{basicLetterId}")
    @Operation(summary = "기본 발음 연습, 시도횟수 업데이트")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "기본 발음 연습, 시도횟수 업데이트 성공"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 유저"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 기본 발음")
    })
    public ResponseEntity tryHistorySaveOrUpdateByBasicLetter(@PathVariable Long basicLetterId,
        Authentication authentication) {
        tryHistoryService.saveOrUpdateByBasicLetter(basicLetterId, 1L);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/try-histories/daily-words/{wordMemberId}")
    @Operation(summary = "일상 단어 발음 연습, 시도횟수 업데이트")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "일상 단어 발음 연습, 시도횟수 업데이트 성공"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 유저"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 일상 단어")
    })
    public ResponseEntity tryHistorySaveOrUpdateByDailyWord(@PathVariable Long wordMemberId,
        Authentication authentication) {
        tryHistoryService.saveOrUpdateBywordMemberId(wordMemberId, 1L);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/try-histories/basic-letters/{basicLetterId}")
    @Operation(summary = "기본 발음 성공 업데이트")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "기본 발음 성공 업데이트"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 유저"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 기본 발음"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 기록")
    })
    public ResponseEntity tryHistoryUpdateByBasicLetter(@PathVariable Long basicLetterId,
        Authentication authentication) {
        tryHistoryService.updateByBasicLetter(basicLetterId, 1L);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/try-histories/daily-words/{dailyWordId}")
    @Operation(summary = "일상 단어 발음 성공 업데이트")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "일상 단어 발음 성공 업데이트"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 유저"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 단어"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 기록")
    })
    public ResponseEntity tryHistoryUpdateByDailyWord(@PathVariable Long wordMemberId,
        Authentication authentication) {
        tryHistoryService.updateBywordMemberId(wordMemberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/basic-letters/{basicLetterId}")
    @Operation(summary = "기본 발음 상세조회")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "기본 발음 상세 조회 성공"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 기본 발음")
    })
    public ResponseEntity<BasicLetterRes> basicLetterDetails(@PathVariable Long basicLetterId,
        Authentication authentication) {
        return ResponseEntity.ok(basicLetterService.findBasicLetter(basicLetterId, 1L));
    }

}
