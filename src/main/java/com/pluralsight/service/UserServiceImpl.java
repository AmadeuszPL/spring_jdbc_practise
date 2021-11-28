package com.pluralsight.service;

import com.pluralsight.model.User;
import com.pluralsight.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.getUser(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    @Transactional
    public void batch() {
        List<User> users = userRepository.getUsers();
        List<Object[]> pairs = new ArrayList<>();

        for (User user : users) {
            Object[] tmp =  {"Hello", user.getId()};
            pairs.add(tmp);
        }

        userRepository.updateUsers(pairs);

        throw new DataAccessException("Testing Exception Handling in batch service") {
        };
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteUser(id);
    }

}
