package com.example.loginduytan.api;

import java.util.List;

public class RoleManager {
    private List<Role> roleList;

    public RoleManager(List<Role> roles) {
        this.roleList = roles;
    }

    // Phương thức để lấy tên vai trò dựa vào ROLE_ID
    public String getRoleNameById(int roleId) {
        for (Role role : roleList) {
            if (role.getROLE_ID() == roleId) {
                return role.getROLE_NAME();
            }
        }
        return null; // Trả về null nếu không tìm thấy vai trò
    }
}
