package ru.clevertec.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.clevertec.eshop.dao.impl.ProductDAOFromFile;
import ru.clevertec.eshop.model.discount.AbstractDiscount;
import ru.clevertec.eshop.model.discount.DiscountCard;

import java.util.List;

@SpringBootApplication
public class EShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(EShopApplication.class, args);
        ProductDAOFromFile productDAOFromFile = new ProductDAOFromFile();
        List<DiscountCard> list = productDAOFromFile.obtainCardsData();
        for (DiscountCard a : list) {
            System.out.println(a.getId() +  " " + a.getDiscountType() + " " + a.getValue());
        }
    }

}
