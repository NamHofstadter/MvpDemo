package com.namcooper.mvp_master.http.model;

public class HttpBaseModel<T> {

    private int status;
    private String message;
    private T data;


    public boolean isSuccess(){
        return status==200;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}