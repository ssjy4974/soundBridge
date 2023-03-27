package com.soundbridge.domain.sentence.controller;

import com.soundbridge.domain.sentence.request.CategoryReq;
import com.soundbridge.domain.sentence.request.CategoryUpdateReq;
import com.soundbridge.domain.sentence.request.QuickSentenceReq;
import com.soundbridge.domain.sentence.request.QuickSentenceUpdateReq;
import com.soundbridge.domain.sentence.response.CategoryRes;
import com.soundbridge.domain.sentence.response.QuickSentenceRes;
import com.soundbridge.domain.sentence.service.CategoryService;
import com.soundbridge.domain.sentence.service.QuickSentenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quick-sentences")
public class QuickSentenceApiController {

    private final QuickSentenceService quickSentenceService;

    @PostMapping
    @Operation(summary = "자주 사용하는 문장 등록")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "자주 사용하는 문장 등록"),
        @ApiResponse(responseCode = "400", description = "이미 존재하는 자주 사용하는 문장"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 유저"),
    })
    public ResponseEntity<List<QuickSentenceRes>> saveQuickSentence(@Valid @RequestBody QuickSentenceReq req,
        Authentication
            authentication) {

        return ResponseEntity.ok(quickSentenceService.saveQuickSentence(req));
    }

    @GetMapping("/{categoryId}")
    @Operation(summary = "자주 사용하는 문장 카테고리 별 조회")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "자주 사용하는 문장 카테고리 별 조회 성공")
    })
    public ResponseEntity<List<QuickSentenceRes>> getQuickSentencesList(@PathVariable Long categoryId,
        Authentication
            authentication) {

        return ResponseEntity.ok(quickSentenceService.getQuickSentencesList(categoryId));
    }

    @DeleteMapping("/{quickSentenceId}")
    @Operation(summary = "자주 사용하는 문장 삭제")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "자주 사용하는 문장 삭제 성공")
    })
    public ResponseEntity<List<QuickSentenceRes>> deleteQuickSentence(@PathVariable Long quickSentenceId,
        Authentication
            authentication) {

        return ResponseEntity.ok(quickSentenceService.deleteQuickSentence(quickSentenceId));
    }

    @PutMapping
    @Operation(summary = "자주 사용하는 문장 수정")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "자주 사용하는 문장 수정 성공")
    })
    public ResponseEntity<List<QuickSentenceRes>> updateQuickSentence(@Valid @RequestBody QuickSentenceUpdateReq req,
        Authentication
            authentication) {

        return ResponseEntity.ok(quickSentenceService.updateQuickSentence(req));
    }

    @PutMapping("/{quickSentenceId}")
    @Operation(summary = "자주 사용하는 문장, 카테고리 count")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "자주 사용하는 문장, 카테고리 count 성공")
    })
    public ResponseEntity<List<QuickSentenceRes>> updateCount(@PathVariable Long quickSentenceId,
        Authentication
            authentication) {

        return ResponseEntity.ok(quickSentenceService.updateCount(quickSentenceId));
    }

}
