package com.example.loginduytan.api;

import java.util.List;

public class RoleResponse {
    private int errorCode;
    private String message;
    private String messageEn;
    private List<Role> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageEn() {
        return messageEn;
    }

    public void setMessageEn(String messageEn) {
        this.messageEn = messageEn;
    }

    public List<Role> getData() {
        return data;
    }

    public void setData(List<Role> data) {
        this.data = data;
    }
}
