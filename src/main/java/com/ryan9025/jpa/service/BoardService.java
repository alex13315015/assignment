package com.ryan9025.jpa.service;

import com.ryan9025.jpa.dto.BoardDto;
import com.ryan9025.jpa.entity.Board02;
import com.ryan9025.jpa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardDto insertBoard(Board02 board02) {
        Board02 board = boardRepository.save(board02);
        return BoardDto.fromEntity(board);
    }

    public List<Board02> getAllBoard() {
       List<Board02> boardList = boardRepository.findAll();
       return boardList;
    }
    // find메서드 사용시 Optional을 들고옴!
    public Board02 getBoard(int id) {
        Optional<Board02> board = boardRepository.findById(id);
        if(board.isPresent()) {
            return board.get();
        }
            //throw new DataNotFoundException("찾는 id가 없음");
        return null;
    }
    public Page<Board02> getAllPageBoard(int page) {
        Pageable pageable = PageRequest.of(page,10,
                Sort.by(Sort.Direction.DESC,"createDate"));
        Page<Board02> board02List = boardRepository.findAll(pageable);
        /*List<BoardDto> boardList = new ArrayList<>();
        for(int i = 0; i < board02List.size(); i++) {
            boardList.add(BoardDto.fromEntity(board02List.get(i)));
        }*/
        return board02List;
    }
    public Page<Board02> getSearchBoard(String category, String keyword, int page) {
        Pageable pageable = PageRequest.of(page,10);
        if(category.equals("subject")) {
            Page<Board02> boardList = boardRepository.findBySubject(keyword,pageable);
            return boardList;
        } else if(category.equals("content")) {
            Page<Board02> boardList = boardRepository.findByContent(keyword,pageable);
            return boardList;
        } else if(category.equals("writer")) {
            Page<Board02> boardList = boardRepository.findByWriter(keyword,pageable);
            return boardList;
        }
        throw new RuntimeException("검색 결과가 없습니다.");

    }
}
