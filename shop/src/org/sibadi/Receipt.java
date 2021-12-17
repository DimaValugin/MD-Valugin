package org.sibadi;

import org.sibadi.product.Product;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public final class Receipt implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final List<Product> productList;
    private final Date date;

    public Receipt() {
        productList = new ArrayList<>();
        date = Calendar.getInstance().getTime();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Date getDate() {
        return date;
    }

    public void remove(Product product) {
        productList.remove(product);
    }

    public void add(Product product) {
        productList.add(product);
    }

    public void clear() {
        productList.clear();
    }

    @Override
    public String toString(){
        String outSting = new String();
        for (Product product : productList) {
            outSting += product.toString() + "\n";
        }
        outSting += date + "\n";
        return  outSting;
    }
}