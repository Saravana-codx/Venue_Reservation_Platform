package com.hall.VenueMgmt.hall.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hall.VenueMgmt.hall.entity.Hall;

public interface HallRepository extends JpaRepository<Hall, Long> {
    List<Hall> findByOwnerEmail(String email);
}