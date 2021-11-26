package com.pluralsight.service;

import com.pluralsight.model.Ride;
import com.pluralsight.model.User;
import com.pluralsight.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rideService")
public class RideServiceImpl implements RideService {

    @Autowired
    private RideRepository rideRepository;

    @Override
    public Ride createRide(Ride ride) {
        return rideRepository.createRide(ride);
    }

    @Override
    public List<Ride> getRides() {
        return rideRepository.getRides();
    }

    @Override
    public Ride getRide(Integer id) {
        return rideRepository.getRide(id);
    }

    @Override
    public Ride updateRide(Ride ride) {
        return rideRepository.updateRide(ride);
    }

}
