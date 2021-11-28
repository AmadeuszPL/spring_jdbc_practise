package com.pluralsight.controller;

import java.util.List;

import com.pluralsight.util.ServiceError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/batchuser", method = RequestMethod.GET)
    public @ResponseBody
    Object batch() {
        userService.batch();
        return null;
    }

    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    Object deleteUser(@PathVariable(value = "id") Integer id) {
        userService.deleteUser(id);
        return null;
    }

    @RequestMapping(value = "/user/test", method = RequestMethod.GET)
    public @ResponseBody
    Object test() {
        throw new DataAccessException("Testing exception thrown") {
        };
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ServiceError> handle(RuntimeException ex) {
        ServiceError error = new ServiceError(HttpStatus.OK.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }


}
