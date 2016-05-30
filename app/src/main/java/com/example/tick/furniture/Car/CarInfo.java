package com.example.tick.furniture.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tick on 2016/5/29.
 */
public class CarInfo{
    private String name,price;

    public CarInfo(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
