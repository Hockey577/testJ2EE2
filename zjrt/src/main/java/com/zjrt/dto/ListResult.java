package com.zjrt.dto;

import java.util.List;

/**
 * Created by Administrator on 2017-8-6.
 */
public class ListResult<T> extends CommonResult {
    private List<T> data;


    public ListResult(boolean success, List<T> data, String state) {
        super();
        this.setSuccess(success);
        this.data = data;
        this.setState(state);
    }

    public ListResult() {
        super();
    }

    public ListResult(boolean success, String state, String error) {
        super();
        this.setSuccess(success);
        this.setState(state);
        this.setError(error);
    }


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
