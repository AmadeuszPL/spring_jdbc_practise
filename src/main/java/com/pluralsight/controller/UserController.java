package com.pluralsight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.pluralsight.model.User;
import com.pluralsight.service.UserService;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public @ResponseBody
    User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public @ResponseBody
    User getUser(@PathVariable(value = "id") Integer id) {
        return userService.getUser(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public @ResponseBody
    User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

}
