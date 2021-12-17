import org.sibadi.Manufacturer;
import org.sibadi.Receipt;
import org.sibadi.Serializer;
import org.sibadi.discount.Discount;
import org.sibadi.discount.DiscountCard;
import org.sibadi.product.Fruit;
import org.sibadi.product.Meat;
import org.sibadi.product.Milk;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException {

        var format = ".data";
        var path = "E:/";
        File file;

        DiscountCard discountCard;
        Receipt receipt;
        Fruit fruit;
        Meat meat;
        Milk milk;

        file = new File(path + "discount" + format);
        if (file.exists()) Discount.load(path + "discount" + format);
        else {

            Discount.getInstance().setMoney(new BigDecimal(3000));

            Discount.getInstance().add(Milk.class, new BigDecimal("0.5"));
            Discount.getInstance().add(Meat.class, new BigDecimal("0.5"));
            Discount.getInstance().add(Fruit.class, new BigDecimal("0.5"));

            System.out.println(Discount.getInstance().toString());

            Discount.getInstance().remove(Fruit.class);

            System.out.println(Discount.getInstance().toString());

            Discount.getInstance().clear();

            System.out.println(Discount.getInstance().toString());

            Discount.getInstance().add(Meat.class, new BigDecimal("0.5"));
            Discount.getInstance().add(Fruit.class, new BigDecimal("0.5"));

            Discount.save(path + "discount" + format);
        }

        System.out.println(Discount.getInstance().toString());

        file = new File(path + "meat" + format);
        if (file.exists()) meat = Serializer.deserialization(path + "meat" + format);
        else meat = new Meat("Meat", new BigDecimal(1000), Manufacturer.RU);

        file = new File(path + "milk" + format);
        if (file.exists()) milk = Serializer.deserialization(path + "milk" + format);
        else milk = new Milk("Milk", new BigDecimal(1000), Manufacturer.US);

        file = new File(path + "fruit" + format);
        if (file.exists()) fruit = Serializer.deserialization(path + "fruit" + format);
        else fruit = new Fruit("Milk", new BigDecimal(1000), Manufacturer.US);

        System.out.println(meat.getName());
        System.out.println(meat.getManufacturer());
        meat.setPrice(meat.getPrice().add(new BigDecimal(2000)));

        System.out.println(meat);
        System.out.println(milk);
        System.out.println(fruit + "\n");

        file = new File(path + "discountCard" + format);
        if (file.exists()) discountCard = Serializer.deserialization(path + "discountCard" + format);
        else discountCard = new DiscountCard("666", "Albert", new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2001"));

        System.out.println(discountCard + "\n"); // Показываем информацию о Карте

        file = new File(path + "receipt" + format);
        if (file.exists()) receipt = Serializer.deserialization(path + "receipt" + format);
        else {
            receipt = new Receipt();
            receipt.add(fruit);
            receipt.add(meat);

            receipt.remove(milk);

            System.out.println(receipt);

            receipt.clear();
        }

        System.out.println(receipt);

        receipt.add(milk);

        System.out.println(receipt.getDate() + "\n");

        System.out.println(discountCard.getDiscount(receipt));

        System.out.println(discountCard + "\n");

        System.out.println(discountCard.getDate() + "\n");
        System.out.println(discountCard.getIdentification() + "\n");
        System.out.println(discountCard.getBirth() + "\n");
        System.out.println(discountCard.getPhone() + "\n");
        System.out.println(discountCard.getProprietor() + "\n");

        System.out.println(discountCard.getDiscount(receipt));

        Serializer.serialization(discountCard, path + "discountCard" + format);
        Serializer.serialization(receipt, path + "receipt" + format);
        Serializer.serialization(fruit, path + "fruit" + format);
        Serializer.serialization(milk, path + "milk" + format);
        Serializer.serialization(meat, path + "meat" + format);

    }
}