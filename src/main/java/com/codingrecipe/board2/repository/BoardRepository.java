package com.codingrecipe.board2.repository;

import com.codingrecipe.board2.dto.BoardDto;
import com.codingrecipe.board2.dto.BoardFileDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

  private final SqlSessionTemplate session;
  private static final String namespace = "Board.";

  public BoardDto save(BoardDto boardDto) {
    session.insert(namespace+"save", boardDto);
    return boardDto;
  }

  public List<BoardDto> findAll() {
    return session.selectList(namespace+"findAll");
  }

  public void updateHits(Long id) {
    session.update(namespace + "updateHits", id);
  }

  public BoardDto findById(Long id) {
    return session.selectOne(namespace+"findById", id);
  }

  public void update(BoardDto boardDto) {
    session.update(namespace + "update", boardDto);
  }

  public void delete(Long id) {
    session.delete(namespace+"delete", id);
  }

  public void saveFile(BoardFileDto boardFileDto) {
    session.insert(namespace + "saveFile", boardFileDto);
  }

  public List<BoardFileDto> findFile(Long id) {
    return session.selectList(namespace + "findFile", id);
  }
}
