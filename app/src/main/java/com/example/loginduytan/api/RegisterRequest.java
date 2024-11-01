package com.example.loginduytan.api;

public class RegisterRequest {
    private String KEY;
    private String USERNAME;
    private String PASSWORD;
    private int ROLE_ID;
    private String CREATE_DATE;

    public RegisterRequest(String KEY, String USERNAME, String PASSWORD, int ROLE_ID, String CREATE_DATE) {
        this.KEY = KEY;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        this.ROLE_ID = ROLE_ID;
        this.CREATE_DATE = CREATE_DATE;
    }

    // Getter và Setter (nếu cần)
    public String getKEY() {
        return KEY;
    }

    public void setKEY(String KEY) {
        this.KEY = KEY;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public int getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(int ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }

    public String getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(String CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }
}
