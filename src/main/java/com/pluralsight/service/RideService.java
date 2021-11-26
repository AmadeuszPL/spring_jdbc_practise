package com.pluralsight.service;

import com.pluralsight.model.Ride;
import com.pluralsight.model.User;

import java.util.List;

public interface RideService {

	Ride createRide(Ride ride);

	List<Ride> getRides();

	Ride getRide(Integer id);

	Ride updateRide(Ride ride);

}