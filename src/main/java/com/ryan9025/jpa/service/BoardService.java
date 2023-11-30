package com.ryan9025.jpa.service;

import com.ryan9025.jpa.entity.Board02;
import com.ryan9025.jpa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board02 insertBoard(Board02 board02) {
        Board02 board = boardRepository.save(board02);
        return board;
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
            return null;
            //throw new DataNotFoundException("찾는 id가 없음");
    }
}
