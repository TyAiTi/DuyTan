package com.example.loginduytan.api;

import java.util.List;

public class RoleManager {
    private List<Role> roleList;

    public RoleManager(List<Role> roles) {
        this.roleList = roles;
    }

    public String getRoleNameById(int roleId) {
        for (Role role : roleList) {
            if (role.getROLE_ID() == roleId) {
                return role.getROLE_NAME();
            }
        }
        return null;
    }

    public Integer getRoleIdByName(String name) {
        for (Role role : roleList) {
            if (role.getROLE_NAME().equals(name)) {  // Sử dụng .equals() để so sánh chuỗi
                return role.getROLE_ID();
            }
        }
        return null; // Đổi kiểu trả về thành Integer để có thể trả về null khi không tìm thấy
    }

}
