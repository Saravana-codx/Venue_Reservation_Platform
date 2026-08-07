package com.hall.VenueMgmt.hall.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class HallRequest {
    private String name;
    private String description;
    private String location;
    private Integer capacity;
    private BigDecimal pricePerDay;
}