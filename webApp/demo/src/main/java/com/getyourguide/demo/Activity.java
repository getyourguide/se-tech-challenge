package com.getyourguide.demo;

import lombok.Data;

@Data
public class Activity {
    private Long id;
    private String title;
    private int price;
    private String currency;
    private double rating;
    private boolean specialOffer;
}
