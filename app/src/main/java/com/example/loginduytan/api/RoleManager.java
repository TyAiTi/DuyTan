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
}
