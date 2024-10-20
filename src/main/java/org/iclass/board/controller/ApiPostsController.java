package org.iclass.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.PostsDTO;
import org.iclass.board.service.PostsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class ApiPostsController {

    private final PostsService postsService;

    /* JPA 구간 */
    // user_id -> username 포함하기 위한 데이터
    @GetMapping
    public ResponseEntity<?> getPostsWithUsers(
        @RequestParam(defaultValue = "1") int page,  // 페이지 번호 (기본값 1)
        @RequestParam(defaultValue = "5") int limit, // 페이지 당 게시글 수 (기본값 5)
        @RequestParam(defaultValue = "") String search,  // 검색어 (기본값 "")
        @RequestParam(defaultValue = "all") String category  // 카테고리 필터 (기본값 "all")
    ) {
        if (page < 1) {
            throw new IllegalArgumentException("Page index must not be less than 1");
        }
        Pageable pageable = PageRequest.of(page - 1, limit);  // 페이지 번호를 0 기반으로 변환
        Page<PostsDTO> dto = postsService.getFilteredPosts(pageable, search, category);
        log.info("dkfjdlfjdlf : {}",dto.toString());
        return ResponseEntity.ok(dto);
    }

    // 게시글 상세 조회
    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostDetail(@PathVariable Long postId) {
        PostsDTO dto = postsService.getPostDetail(postId);
        return ResponseEntity.ok(dto);
    }
}

