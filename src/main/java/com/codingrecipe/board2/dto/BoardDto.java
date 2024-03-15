package com.codingrecipe.board2.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
  private Long id;
  private String boardWriter;
  private String boardPass;
  private String boardTitle;
  private String boardContents;
  private int boardHits;
  private String createdAt;
  private int fileAttached;
  private List<MultipartFile> boardFile;
}
