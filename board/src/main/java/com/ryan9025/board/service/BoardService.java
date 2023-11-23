package com.ryan9025.board.service;


import com.ryan9025.board.dao.BoardDao;
import com.ryan9025.board.dto.BoardDto;
import com.ryan9025.board.dto.Criteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
    // spring container 에 등록된 bean을 주입받을때 쓴다.
    private final BoardDao boardDao;

//    public BoardService(BoardDao boardDao) {
//        this.boardDao = boardDao;
//    }

    /*public List<BoardDto> getAllBoard(String category, String searchTxt) {
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("category",category);
        hashMap.put("searchTxt",searchTxt);
        List<BoardDto> boardList = boardDao.getAllBoard(hashMap);
        return boardList;
    }*/

    public List<BoardDto> getAllBoard(Criteria criteria) {
        List<BoardDto> boardList = boardDao.getAllBoard(criteria);
        return boardList;
    }
    public int insertBoard(BoardDto boardDto) {
        int result = boardDao.insertBoard(boardDto);
        return result;
    }

    public int deleteBoard(int id) {
        int result = boardDao.deleteBoard(id);
        return result;
    }

    public BoardDto getOneBoard(int id) {
        BoardDto result = boardDao.getOneBoard(id);
        return result;
    }

    public int modifyBoard(BoardDto boardDto) {
        int result = boardDao.modifyBoard(boardDto);
        return result;
    }

    public int getTotalCount(Criteria criteria) {
        int result = boardDao.getTotalCount(criteria);
        return  result;
    }



//    public BoardDto getOneBoard(String name) {
//        BoardDto boardDto = boardDao.getOneBoard(name);
//        return boardDto;
//    }

}
