package com.anton.web_project.model.service.impl;

import com.anton.web_project.model.entity.User;
import com.anton.web_project.model.service.AdminService;

import java.util.List;

public class AdminServiceImplementation implements AdminService {
    private static AdminServiceImplementation instance = new AdminServiceImplementation();

    public static AdminServiceImplementation getInstance() {
        return instance;
    }

    private AdminServiceImplementation() {

    }

    @Override
    public boolean blockUser(String username) {
        return false;
    }

    @Override
    public boolean unblockUser(String username) {
        return false;
    }

    @Override
    public List<User> viewUsers() {
        return null;
    }
}
