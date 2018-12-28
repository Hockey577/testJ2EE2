package com.zjrt.dao;

/**
 * Created by Administrator on 2018-2-23.
 */
public class LimitVO {
    private long startRow;
    private long pageSize;

    @Override
    public String toString() {
        return "LimitVO{" +
                "startRow=" + startRow +
                ", pageSize=" + pageSize +
                '}';
    }

    public LimitVO() {
    }

    public LimitVO(long startRow, long pageSize) {
        this.startRow = startRow;
        this.pageSize = pageSize;
    }

    public long getStartRow() {
        return startRow;
    }

    public void setStartRow(long startRow) {
        this.startRow = startRow;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }
}
