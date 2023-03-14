package com.soundbridge.domain.board.repository;

import com.soundbridge.domain.board.response.BoardDetailRes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BoardRepositorySupport {

    Slice<BoardDetailRes> findAllWithPaging(Pageable pageable, Long cursorId, Long memberId);
}
