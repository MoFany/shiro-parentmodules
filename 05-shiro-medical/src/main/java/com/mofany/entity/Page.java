package com.mofany.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author MoFany-J
 * @date 2022/11/30
 * @description Page
 */
@Setter
@Getter
@ToString
public class Page implements Serializable {
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 一页记录数
     */
    private Integer pageSize;
    /**
     * 总记录数
     */
    private Integer totalCount;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 起始页
     */
    private Integer startIndex;

    public Page(Integer currentPage, Integer totalCount,Integer pageSize) {
        this.currentPage = currentPage;
        this.totalCount = totalPage;
        this.pageSize=pageSize;
        calc(currentPage,totalCount);
    }

    private void calc(Integer currentPage, Integer totalCount) {
        //计算总页数
        this.totalPage = (totalCount % this.pageSize) ==
                0 ? (totalCount / this.pageSize) : (totalCount / this.pageSize + 1);
        //计算起始页
        this.startIndex = (currentPage - 1) * this.pageSize;
    }


}
