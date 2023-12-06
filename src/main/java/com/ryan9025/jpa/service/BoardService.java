package com.ryan9025.jpa.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryan9025.jpa.dto.BoardDto;
import com.ryan9025.jpa.entity.Board02;
import com.ryan9025.jpa.entity.QBoard02;
import com.ryan9025.jpa.exception.DataNotFoundException;
import com.ryan9025.jpa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final JPAQueryFactory queryFactory;

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
            throw new DataNotFoundException("찾는 id가 없음");
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
        } else {
            Page<Board02> boardList = boardRepository.findByAllCategory(keyword,pageable);
            return boardList;
        }
    }

    public Board02 getBoardDsl(int id) {
        QBoard02 qBoard = QBoard02.board02;
        Board02 selectedBoard02 =
                queryFactory.select(qBoard).from(qBoard).where(qBoard.id.eq(id)).fetchOne();
        return selectedBoard02;
    }

    public List<Board02> getAllBoardDsl() {
        QBoard02 qBoard = QBoard02.board02;
        List<Board02> boardList = queryFactory.selectFrom(qBoard).fetch();
        return boardList;
    }

    public Page<Board02> getAllPageBoardDsl(int page) {
        QBoard02 qBoard = QBoard02.board02;
        Pageable pageable = PageRequest.of(page,10,
                Sort.by(Sort.Direction.DESC,"createDate"));
        List<Board02> boardList = queryFactory
                .selectFrom(qBoard)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(qBoard.count()).from(qBoard).fetchOne();

        return new PageImpl<>(boardList,pageable,total);
    }

    public Page<Board02> getSearchBoardDsl(String category, String keyword, int page) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QBoard02 qBoard = QBoard02.board02;
        Pageable pageable = PageRequest.of(page,10,
                Sort.by(Sort.Direction.DESC,"createDate"));

        if(StringUtils.equals(category,"subject")) {
            log.info("subject검색");
            booleanBuilder.and(qBoard.subject.contains(keyword));

        }
        if(StringUtils.equals(category,"content")) {
            log.info("content검색");
            booleanBuilder.and(qBoard.content.contains(keyword));

        }
        if(StringUtils.equals(category,"writer")) {
            log.info("writer검색");
            booleanBuilder.and(qBoard.writer.nickName.contains(keyword));

        }


        List<Board02> boardList = queryFactory
                .selectFrom(qBoard)
                .where(qBoard.subject.contains(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long total = queryFactory.select(qBoard.count()).from(qBoard).fetchOne();

        return new PageImpl<>(boardList,pageable,total);
    }

}
