package com.example.loginduytan.api;

public class UserData {
    private int ACCOUNT_ID;
    private String USERNAME;
    private String CREATE_DATE;
    private String CREATE_BY_USERNAME;
    private String AVATAR;
    private int ROLE_ID;
    private String ROLE_NAME;
    private String ROLE_CREATE_BY_USERNAME;
    private String ROLE_CREATE_DATE;

    // Getter và Setter cho các trường
    public int getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(int ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(String CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }

    public String getCREATE_BY_USERNAME() {
        return CREATE_BY_USERNAME;
    }

    public void setCREATE_BY_USERNAME(String CREATE_BY_USERNAME) {
        this.CREATE_BY_USERNAME = CREATE_BY_USERNAME;
    }

    public String getAVATAR() {
        return AVATAR;
    }

    public void setAVATAR(String AVATAR) {
        this.AVATAR = AVATAR;
    }

    public int getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(int ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }

    public String getROLE_NAME() {
        return ROLE_NAME;
    }

    public void setROLE_NAME(String ROLE_NAME) {
        this.ROLE_NAME = ROLE_NAME;
    }

    public String getROLE_CREATE_BY_USERNAME() {
        return ROLE_CREATE_BY_USERNAME;
    }

    public void setROLE_CREATE_BY_USERNAME(String ROLE_CREATE_BY_USERNAME) {
        this.ROLE_CREATE_BY_USERNAME = ROLE_CREATE_BY_USERNAME;
    }

    public String getROLE_CREATE_DATE() {
        return ROLE_CREATE_DATE;
    }

    public void setROLE_CREATE_DATE(String ROLE_CREATE_DATE) {
        this.ROLE_CREATE_DATE = ROLE_CREATE_DATE;
    }
}
