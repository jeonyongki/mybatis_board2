package com.codingrecipe.board2.service;

import com.codingrecipe.board2.dto.BoardDto;
import com.codingrecipe.board2.dto.BoardFileDto;
import com.codingrecipe.board2.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardRepository boardRepository;

//  public void save(BoardDto boardDto) {
//    boardRepository.save(boardDto);
//  }

  public void save(BoardDto boardDto) throws IOException {
    //첨부파일 없음.
    if(boardDto.getBoardFile().get(0).isEmpty()){
      boardDto.setFileAttached(0);
      boardRepository.save(boardDto);
    }
    else{//첨부파일 있음 로직..
      boardDto.setFileAttached(1);
      //게시글 저장후 id값 활용을 위한 리턴객체.
      BoardDto saveBoard = boardRepository.save(boardDto);
      //해당파일 가져오기.
      for(MultipartFile boardFile : boardDto.getBoardFile()) {
        //파일원본 이름 가져오기
        String originalFilename = boardFile.getOriginalFilename();
        System.out.println("originalFilename = " + originalFilename);
        //파일저장용 이름 만들기
        String storedFileName = System.currentTimeMillis() + "-" + originalFilename;
        System.out.println("storedFileName = " + storedFileName);
        BoardFileDto boardFileDto = BoardFileDto.builder()
            .boardId(saveBoard.getId())
            .originalFileName(originalFilename)
            .storedFileName(storedFileName).build();
        //로컬에 파일 저장처리.
        String savePath = "d:/temp/board2/" + storedFileName;
        boardFile.transferTo(new File(savePath));
        //db에 저장.
        boardRepository.saveFile(boardFileDto);
      }
    }
  }

  public List<BoardDto> findAll() {
    return boardRepository.findAll();
  }

  public void updateHits(Long id) {
    boardRepository.updateHits(id);
  }

  public BoardDto findById(Long id) {
    return boardRepository.findById(id);
  }

  public void update(BoardDto boardDto) {
    boardRepository.update(boardDto);
  }

  public void delete(Long id) {
    boardRepository.delete(id);
  }

  public List<BoardFileDto> findFile(Long id) {
    return boardRepository.findFile(id);
  }
}
