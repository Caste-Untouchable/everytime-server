package com.untouchable.everytime.Board.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "게시글 조회용 DTO")
public class BoardResponseDTO {
    @Schema(description = "게시글 PK")
    private Long boardPK;
    @Schema(description = "게시글 소속 학교")
    private String schoolSchoolName;
    @Schema(description = "게시글 작성자")
    private String userUserId;
    @Schema(description = "게시글 작성자 닉네임")
    private String userUserNickname;
    @Schema(description = "게시글 종류 pk")
    private Long boardTypePK;
    @Schema(description = "게시글이름")
    private String boardTitle;
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "게시글 추천수")
    private int recommendCount;
    @Schema(description = "게시글 생성 시간")
    private Timestamp createdAT;
    @Schema(description = "익명 여부")
    private boolean anonymity;
    @Schema(description = "신고 수")
    private Long reportCount;
    @Schema(description = "댓글 수")
    private Long commentCount;
    @Schema(description = "스크랩 수")
    private Long scrapCount;

    //@Schema(description = "게시글 이미지");
    //ArrayList<BoardImageDTO> images;
}
