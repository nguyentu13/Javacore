package com.xtel.vngolf.api.listener.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GolfTour {
    private int id;
    private String avatar;
    private String tour_name;
    private float duration_time;
    private String address;
    private int round;
    private double price;
}
