package org.sibadi.product;

import org.sibadi.Manufacturer;

import javax.print.attribute.standard.PDLOverrideSupported;
import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Manufacturer manufacturer;
    private final String name;

    private BigDecimal price;

    public Product(String name,BigDecimal price, Manufacturer manufacturer){
        this.manufacturer = manufacturer;
        this.price = price;
        this.name = name;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }


    public BigDecimal getPrice(){
        return price;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return name + " " + price + " " + manufacturer;
    }
}