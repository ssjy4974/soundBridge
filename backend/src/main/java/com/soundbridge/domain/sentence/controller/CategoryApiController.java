package com.soundbridge.domain.sentence.controller;

import com.soundbridge.domain.sentence.request.CategoryReq;
import com.soundbridge.domain.sentence.request.CategoryUpdateReq;
import com.soundbridge.domain.sentence.response.CategoryRes;
import com.soundbridge.domain.sentence.service.CategoryService;
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
@RequestMapping("/api/categories")
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping
    @Operation(summary = "카테고리 등록")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "카테고리 등록"),
        @ApiResponse(responseCode = "400", description = "이미 존재하는 카테고리"),
        @ApiResponse(responseCode = "404", description = "존재 하지 않는 유저"),
    })
    public ResponseEntity<List<CategoryRes>> saveCategoryName(@Valid @RequestBody CategoryReq req,
        Authentication
            authentication) {
        System.out.println(req.getCategoryName());
        return ResponseEntity.ok(categoryService.saveCategoryName(req, 1L));
    }

    @GetMapping
    @Operation(summary = "카테고리 전체 조회")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "카테고리 전체 조회 성공")
    })
    public ResponseEntity<List<CategoryRes>> getCategoryList(
        Authentication
            authentication) {

        return ResponseEntity.ok(categoryService.getCategoryList(1L));
    }

    @DeleteMapping("/{categoryId}")
    @Operation(summary = "카테고리 삭제")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "카테고리 삭제 성공")
    })
    public ResponseEntity<List<CategoryRes>> deleteCategory(@PathVariable Long categoryId,
        Authentication
            authentication) {

        return ResponseEntity.ok(categoryService.deleteCategory(categoryId, 1L));
    }

    @PutMapping
    @Operation(summary = "카테고리 수정")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "카테고리 수정 성공")
    })
    public ResponseEntity<List<CategoryRes>> deleteCategory(@Valid @RequestBody CategoryUpdateReq req,
        Authentication
            authentication) {

        return ResponseEntity.ok(categoryService.updateCategoryName(req, 1L));
    }

}
