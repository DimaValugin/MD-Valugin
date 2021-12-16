package org.sibadi.product;

import org.sibadi.Manufacturer;

import java.math.BigDecimal;

public class Meat extends Product {

    public Meat(String name, BigDecimal price, Manufacturer manufacturer) {
        super(name, price, manufacturer);
    }
}