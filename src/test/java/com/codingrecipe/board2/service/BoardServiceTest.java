package com.codingrecipe.board2.service;

import com.codingrecipe.board2.dto.BoardDto;
import org.junit.jupiter.api.Test;


class BoardServiceTest {

  @Test
  public void saveTest() {
      BoardDto dto = BoardDto.builder().boardTitle("tester")
          .boardWriter("테스터")
          .boardPass("1234")
          .boardContents("no contents..")
          .build();
      System.out.println("dto = " + dto.toString());
  }
}