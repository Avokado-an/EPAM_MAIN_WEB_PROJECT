package com.anton.web_project.model.service;

import com.anton.web_project.model.entity.User;

import java.util.List;

public interface AdminService {
    boolean blockUser(String username);
    boolean unblockUser(String username);
    List<User> viewUsers();
}
