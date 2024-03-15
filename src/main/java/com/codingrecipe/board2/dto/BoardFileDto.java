package com.codingrecipe.board2.dto;

import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardFileDto {
  private Long id;
  private Long boardId;
  private String originalFileName;
  private String storedFileName;
}