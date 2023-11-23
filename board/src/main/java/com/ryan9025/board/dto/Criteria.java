package com.ryan9025.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {

    private int currentPage;
    private int pageSize;    //한 페이지에 몇 개 띄울건지
    private String category;
    private String searchText;

    public Criteria() {
        this.currentPage = 1;
        this.pageSize = 10;
    }

    public int getStartPage() {
        // 1~10, 11~20, 21~30...
        return (currentPage - 1) * pageSize + 1;
    }

    public int getEndPage() {

        return currentPage * pageSize;
    }
}
