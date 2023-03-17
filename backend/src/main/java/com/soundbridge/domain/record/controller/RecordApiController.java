package com.soundbridge.domain.record.controller;


import com.soundbridge.domain.record.response.NextRecordSentenceRes;
import com.soundbridge.domain.record.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "mebmer", description = "record API 입니다.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/record")
public class RecordApiController {
    private final RecordService recordService;

    @Operation(summary = "다음에 진행할 녹음 조회", description = "다음에 진행할 녹음 조회 메소드 입니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "이력 조회 성공"),
        @ApiResponse(responseCode = "400", description = "필수값 누락"),
        @ApiResponse(responseCode = "401", description = "권함 없음, 부족"),
        @ApiResponse(responseCode = "403", description = "권한 없음"),
        @ApiResponse(responseCode = "404", description = "존재하지않는 유저 정보"),
    })
    @GetMapping("/{memberId}")
    public ResponseEntity getNextRecord(@PathVariable Long memberId) {
        NextRecordSentenceRes myNextRecord = recordService.getMyNextRecord(memberId);

        log.info("get Next Record {}", myNextRecord);

        return ResponseEntity.ok().body(myNextRecord);
    }

}