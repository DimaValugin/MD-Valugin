package org.sibadi.discount;

import org.sibadi.Receipt;
import org.sibadi.product.Product;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class DiscountCard implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String identification;
    private final Date birth;
    private final Date date;

    private String proprietor;
    private BigDecimal money;
    private String phone;

    public DiscountCard(String phone, String proprietor, Date birth) {
        identification = UUID.randomUUID().toString();
        date = Calendar.getInstance().getTime();
        money = new BigDecimal(0);

        this.birth = birth;

        setProprietor(proprietor);
        setPhone(phone);
    }

    public BigDecimal getDiscount(Receipt receipt) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Product product : receipt.getProductList()) {
            if (Discount.getInstance().getDiscounts().containsKey(product.getClass()) && money.compareTo(Discount.getInstance().getMoney()) >= 0)
                totalPrice = totalPrice.add(product.getPrice().multiply(Discount.getInstance().getDiscounts().get(product.getClass())));
            else totalPrice = totalPrice.add(product.getPrice());
        }
        money = money.add(totalPrice);
        return totalPrice;
    }

    public void setProprietor(String proprietor) {
        this.proprietor = proprietor;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentification() {
        return identification;
    }

    public String getProprietor() {
        return proprietor;
    }

    public String getPhone() {
        return phone;
    }

    public Date getBirth() {
        return birth;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString(){
        return proprietor + " " + birth + " " + phone + " " + date + " " + identification + " " + money;
    }
}