package com.hall.VenueMgmt.hall.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.hall.VenueMgmt.hall.dto.HallRequest;
import com.hall.VenueMgmt.hall.entity.Hall;
import com.hall.VenueMgmt.hall.repository.HallRepository;
import com.hall.VenueMgmt.hall.service.HallService;
import com.hall.VenueMgmt.user.entity.User;
import com.hall.VenueMgmt.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;
    private final UserRepository userRepository;

    @Override
    public Hall createHall(HallRequest request, String ownerEmail) {
        User owner = userRepository.findByEmail(ownerEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Hall hall = Hall.builder()
                .name(request.getName())
                .description(request.getDescription())
                .location(request.getLocation())
                .capacity(request.getCapacity())
                .pricePerDay(request.getPricePerDay())
                .owner(owner)
                .build();

        return hallRepository.save(hall);
    }

    @Override
    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    @Override
    public List<Hall> getMyHalls(String ownerEmail) {
        return hallRepository.findByOwnerEmail(ownerEmail);
    }
}