package com.pluralsight.service;

import com.pluralsight.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getUsers();

    User getUser(Integer id);

    User updateUser(User user);

    void batch();

    void deleteUser(Integer id);

}
