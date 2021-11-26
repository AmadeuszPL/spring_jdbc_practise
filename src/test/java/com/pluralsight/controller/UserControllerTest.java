package com.pluralsight.controller;

import com.pluralsight.model.User;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class UserControllerTest {

    @Test(timeout = 3000)
    public void testCreateUser() {
        RestTemplate restTemplate = new RestTemplate();

        User user = new User();
        user.setFirstName("Ann");
        user.setLastName("Blowwind");
        user.setAge(69);
        user.setEmail("ann@fromtheblowingwind.ru");

        user = restTemplate.postForObject("http://localhost:8080/ride_tracker/user", user, User.class);
        System.out.println("User: " + user);
    }

    @Test(timeout = 3000)
    public void testGetUsers() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<User>> usersResponse = restTemplate.exchange(
                "http://localhost:8080/ride_tracker/users", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<User>>() {
                });
        List<User> users = usersResponse.getBody();

        for (User user : users) {
            System.out.println("User name: " + user.getFirstName() + " " + user.getLastName());
        }
    }

    @Test(timeout = 3000)
    public void testGetUser() {
        RestTemplate restTemplate = new RestTemplate();

        User user = restTemplate.getForObject("http://localhost:8080/ride_tracker/user/5", User.class);
        System.out.println("User names: " + user.getFirstName() + " " + user.getLastName());
    }

    @Test(timeout = 3000)
    public void testUpdate() {
        RestTemplate restTemplate = new RestTemplate();

        User user = restTemplate.getForObject("http://localhost:8080/ride_tracker/user/3", User.class);

        System.out.println("User age before update: " + user.getAge());

        user.setAge(user.getAge() + 5);

        restTemplate.put("http://localhost:8080/ride_tracker/user", user);

        System.out.println("User age after update: " + user.getAge());
    }

}
