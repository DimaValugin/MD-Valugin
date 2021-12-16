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

            Discount.getInstance().setMoney(new BigDecimal(3000)); // Задаём необходимую сумму для получения статуса постоянного покупателя

            Discount.getInstance().add(Milk.class, new BigDecimal("0.5")); // Добавляем скидку в 50% для Молока
            Discount.getInstance().add(Meat.class, new BigDecimal("0.5")); // Добавляем скидку в 50% для Мяса
            Discount.getInstance().add(Fruit.class, new BigDecimal("0.5")); // Добавляем скидку в 50% для Фруктов

            System.out.println(Discount.getInstance().toString()); // Показываем информацию о скидках

            Discount.getInstance().remove(Fruit.class); // Удаляем скидку в 50% для Фруктов

            System.out.println(Discount.getInstance().toString()); // Показываем информацию о скидках

            Discount.getInstance().clear(); // Удаляем все скидки

            System.out.println(Discount.getInstance().toString()); // Показываем информацию о скидках

            Discount.getInstance().add(Meat.class, new BigDecimal("0.5")); // Добавляем скидку в 50% для Мяса
            Discount.getInstance().add(Fruit.class, new BigDecimal("0.5")); // Добавляем скидку в 50% для Фруктов

            Discount.save(path + "discount" + format);
        }

        System.out.println(Discount.getInstance().toString()); // Показываем информацию о скидках

        file = new File(path + "meat" + format);
        if (file.exists()) meat = Serializer.deserialization(path + "meat" + format);
        else meat = new Meat("Meat", new BigDecimal(1000), Manufacturer.RU);

        file = new File(path + "milk" + format);
        if (file.exists()) milk = Serializer.deserialization(path + "milk" + format);
        else milk = new Milk("Milk", new BigDecimal(1000), Manufacturer.US);

        file = new File(path + "fruit" + format);
        if (file.exists()) fruit = Serializer.deserialization(path + "fruit" + format);
        else fruit = new Fruit("Milk", new BigDecimal(1000), Manufacturer.US);

        System.out.println(meat.getName()); // Это точно то самое мясо?
        System.out.println(meat.getManufacturer()); // А кто произвёл?
        meat.setPrice(meat.getPrice().add(new BigDecimal(2000))); // Понятно. Поднимаем цену - русское мясо же!

        System.out.println(meat); // Показываем информации о Мясе
        System.out.println(milk); // Показываем информации о Молоке
        System.out.println(fruit + "\n"); // Показываем информации о Фрукте

        file = new File(path + "discountCard" + format);
        if (file.exists()) discountCard = Serializer.deserialization(path + "discountCard" + format);
        else discountCard = new DiscountCard("666", "Albert", new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2001"));

        System.out.println(discountCard + "\n"); // Показываем информацию о Карте

        file = new File(path + "receipt" + format);
        if (file.exists()) receipt = Serializer.deserialization(path + "receipt" + format);
        else {
            receipt = new Receipt(); // Создаём чек
            receipt.add(fruit); // Включаем в чек Фрукт
            receipt.add(meat); // Включаем в чек Мясо

            receipt.remove(milk); // Удаляем из чека Молоко

            System.out.println(receipt); // Выводим информацию о чеке

            receipt.clear(); // Удаляем все продукты чека
        }

        System.out.println(receipt); // Выводим информацию о чеке

        receipt.add(milk); // Включаем в чек Молоко

        System.out.println(receipt.getDate() + "\n"); // Показываем дату чека

        System.out.println(discountCard.getDiscount(receipt)); // Показываем стоимость покупки без скидки

        System.out.println(discountCard + "\n"); // Показываем информацию о Карте

        System.out.println(discountCard.getDate() + "\n"); // Какая дата регистрации карты?
        System.out.println(discountCard.getIdentification() + "\n"); // Какие там цифры у карты?
        System.out.println(discountCard.getBirth() + "\n"); // Когда там день рождения у владельца карты?
        System.out.println(discountCard.getPhone() + "\n"); // Какой там номер телефона у владельца карты?
        System.out.println(discountCard.getProprietor() + "\n"); // А кто владелец карты то?

        System.out.println(discountCard.getDiscount(receipt)); // Показываем стоимость покупки уже со скидкой

        Serializer.serialization(discountCard, path + "discountCard" + format);
        Serializer.serialization(receipt, path + "receipt" + format);
        Serializer.serialization(fruit, path + "fruit" + format);
        Serializer.serialization(milk, path + "milk" + format);
        Serializer.serialization(meat, path + "meat" + format);

        // Вот и все реализованные методы нашли своё место :)
    }
}