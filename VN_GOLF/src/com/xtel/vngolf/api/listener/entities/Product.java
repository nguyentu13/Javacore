package com.xtel.vngolf.api.listener.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Product {
    private int id;
    private String avatar;
    private String name;
    private int category_id;
}
