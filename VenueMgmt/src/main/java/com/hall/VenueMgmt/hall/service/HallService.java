package com.hall.VenueMgmt.hall.service;

import java.util.List;
import com.hall.VenueMgmt.hall.dto.HallRequest;
import com.hall.VenueMgmt.hall.entity.Hall;

public interface HallService {
    Hall createHall(HallRequest request, String ownerEmail);
    List<Hall> getAllHalls();
    List<Hall> getMyHalls(String ownerEmail);
}