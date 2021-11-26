package com.pluralsight;

import com.pluralsight.model.Ride;
import com.pluralsight.model.User;

class InnerTest {

    public static void main(String[] args) {
        Ride ride = new Ride();
        User user = new User();

        ride.setName("Nejm");
        user.setFirstName("Nejm");

        System.out.println(ride);
        System.out.println(user);
    }

}
