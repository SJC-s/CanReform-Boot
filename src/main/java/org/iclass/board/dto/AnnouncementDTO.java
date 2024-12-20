package org.iclass.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iclass.board.entity.AnnouncementEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AnnouncementDTO {
    private Long announcementId;
    private String userId;
    private String title;
    private String content;
    private String category;
    private String filenames;
    private Integer readCount;
    private Integer commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 업로드 파일을 저장하기 위한 객체
    private MultipartFile file;
    //한번에 여러개의 파일을 업로드
    private List<MultipartFile> fileS;


    public static AnnouncementDTO of(AnnouncementEntity entity) {
        return AnnouncementDTO.builder()
                .announcementId(entity.getAnnouncementId())
                .userId(entity.getUserId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .category(entity.getCategory())
                .filenames(entity.getFilenames())
                .readCount(entity.getReadCount() != null ? entity.getReadCount() : 0) // 기본값 설정
                .commentCount(entity.getCommentCount() != null ? entity.getCommentCount() : 0) // 기본값 설정
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public AnnouncementEntity toEntity() {
        return AnnouncementEntity.builder()
                .announcementId(announcementId)
                .userId(userId)
                .title(title)
                .content(content)
                .category(category)
                .filenames(filenames)
                .readCount(readCount != null ? readCount : 0) // 기본값 설정
                .commentCount(commentCount != null ? commentCount : 0) // 기본값 설정
                .updatedAt(updatedAt)
                .build();
    }

}