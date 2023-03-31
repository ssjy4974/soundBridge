package com.soundbridge.domain.sentence.controller;

import com.soundbridge.domain.member.response.MemberAccessRes;
import com.soundbridge.domain.sentence.request.MySentenceLogReq;
import com.soundbridge.domain.sentence.response.MySentenceLogRes;
import com.soundbridge.domain.sentence.service.MySentenceLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SentenceApiController {

    private final MySentenceLogService sentenceLogService;

    @PostMapping("/my-sentence-logs")
    @Operation(summary = "내가 쓴 문장 저장, 업데이트")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "내가 쓴 문장 저장, 업데이트 성공"),
        @ApiResponse(responseCode = "400", description = "이미 존재하는 자원"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 유저"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 문장")
    })
    public ResponseEntity mySentenceLogSaveOrUpdate(@Valid @RequestBody MySentenceLogReq req,
        Authentication
            authentication) {
        Long memberId = ((MemberAccessRes) authentication.getPrincipal()).getId();
        sentenceLogService.saveOrUpdateMySentenceLog(req, memberId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/my-sentence-logs/{sentence}")
    @Operation(summary = "내가 쓴 문장 자동완성 조회")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "내가 쓴 문장 자동완성 조회 성공")
    })
    public ResponseEntity<List<MySentenceLogRes>> mySentenceLogList(
        @PathVariable String sentence,
        Authentication
            authentication) {
        Long memberId = ((MemberAccessRes) authentication.getPrincipal()).getId();
        return ResponseEntity.ok(sentenceLogService.findMySentenceLog(sentence, memberId));
    }

}
