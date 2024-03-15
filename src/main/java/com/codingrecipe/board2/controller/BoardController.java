package com.codingrecipe.board2.controller;

import com.codingrecipe.board2.dto.BoardDto;
import com.codingrecipe.board2.dto.BoardFileDto;
import com.codingrecipe.board2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor//final 필드만 생성자를 만들어줌.
public class BoardController {
  private final BoardService boardService;

  @GetMapping("/save")
  public String save(){
    return "save";
  }
  @PostMapping("/save")
  public String save(BoardDto boardDto) throws IOException {
    System.out.println("boardDto = " + boardDto);
    boardService.save(boardDto);
    return "redirect:/list";
  }

  @GetMapping("/list")
  public String findAll(Model model){
    List<BoardDto> boardDtoList = boardService.findAll();
    model.addAttribute("boardList", boardDtoList);
    System.out.println("boardDtoList = " + boardDtoList);
    return "list";
    }
    // /10, /1, 게시글 글번호로 경로변수 설정.
  @GetMapping("{id}")
  public String findById(@PathVariable("id") Long id, Model model){
    //조회수처리
    boardService.updateHits(id);
    //상세내용 가져옴
    BoardDto boardDto = boardService.findById(id);
    model.addAttribute("board", boardDto);
    System.out.println("boardDto = " + boardDto);
    if(boardDto.getFileAttached()!=0){
      List<BoardFileDto> boardFileDtoList = boardService.findFile(id);
      model.addAttribute("boardFileList", boardFileDtoList);
    }
    return "detail";
  }

  @GetMapping("/update/{id}")
  public String update(@PathVariable("id") Long id, Model model){
    BoardDto boardDto = boardService.findById(id);
    model.addAttribute("board", boardDto);
    return "update";
  }

  @PostMapping("/update/{id}")
  public String update(BoardDto boardDto, Model model){
    boardService.update(boardDto);
    BoardDto updateDto = boardService.findById(boardDto.getId());
    model.addAttribute("board", updateDto);
    return "detail";
  }
  @GetMapping("/delete/{id}")
  public String delete(@PathVariable("id") Long id){
    boardService.delete(id);
    System.out.println("id = " + id);
    return "redirect:/list";
  }
}
