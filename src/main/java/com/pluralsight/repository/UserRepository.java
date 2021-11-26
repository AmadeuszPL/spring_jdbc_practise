package com.pluralsight.repository;

import com.pluralsight.model.User;

import java.util.List;

public interface UserRepository {

    User createUser(User user);

    List<User> getUsers();

    User getUser(Integer id);

    User updateUser(User user);

}
