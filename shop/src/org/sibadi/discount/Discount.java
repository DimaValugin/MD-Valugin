package org.sibadi.discount;

import org.sibadi.Serializer;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public final class Discount implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<Class, BigDecimal> discounts = new HashMap<Class, BigDecimal>();
    private static Discount instance = new Discount();
    private BigDecimal money = new BigDecimal(0);

    private Discount() {
    }

    public static void load(String path) throws IOException, ClassNotFoundException {
        instance = Serializer.<Discount>deserialization(path);
    }

    public static void save(String path) throws IOException {
        Serializer.serialization(instance, path);
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public static Discount getInstance() {
        return instance;
    }

    public final Map<Class, BigDecimal> getDiscounts() {
        return discounts;
    }

    public final <T extends Class, Product> void add(T product, BigDecimal discount) {
        discounts.put(product, discount);
    }

    public final <T extends Class, Product> void remove(T product) {
        discounts.remove(product);
    }

    public final <T extends Class, Product> void clear() {
        discounts.clear();
    }

    @Override
    public String toString() {
        String outSting = new String();
        for (Class discount : discounts.keySet())
            outSting += discount.getName() + " " + discounts.get(discount) + "\n";
        outSting += money + "\n";
        return outSting;
    }
}