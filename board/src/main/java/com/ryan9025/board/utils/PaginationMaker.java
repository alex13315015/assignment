package com.ryan9025.board.utils;

import com.ryan9025.board.dto.Criteria;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@ToString
@Slf4j
public class PaginationMaker {
        private Criteria criteria; //기준점!!
        private int total;         //총 갯수
        private int startPage;     // 시작 페이지
        private int endPage;       // 마지막 페이지
        private int pageBlock = 7;     //1/2/3/4/5
        //private int lastPage;


        private boolean isPrev;  // 이전 페이지
        private boolean isNext;  // 다음 페이지

        private int count;


        //전체 페이지 갯수를 정하는 순간 makePagination이 만들어진다...
        public void setTotal (int total) {
                this.total = total;
                log.info("total==={}",total);
                makePagination();
        }

        public int lastPage () {
                return (int) Math.ceil(total / (double) criteria.getPageSize());
        }
        private void makePagination() {
                endPage = (int) Math.ceil(  (criteria.getCurrentPage() / (double) pageBlock) ) * pageBlock ;
                startPage = (endPage - pageBlock) + 1;
                count = total-(criteria.getCurrentPage() - 1)*criteria.getPageSize();
                int lastPage = (int) Math.ceil(total / (double) criteria.getPageSize());
                if(endPage > lastPage) endPage = lastPage;
                isPrev = startPage == 1 ? false : true;
                isNext = lastPage > endPage ? true : false;
        }
}
