package ru.clevertec.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.clevertec.eshop.dao.impl.file.PromoDAOFromFile;
import ru.clevertec.eshop.model.card.DiscountCard;
import ru.clevertec.eshop.model.promo.Promo;

import java.util.List;

@SpringBootApplication
public class EShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(EShopApplication.class, args);
        PromoDAOFromFile productDAOFromFile = new PromoDAOFromFile();
        List<Promo> discountPromos = productDAOFromFile.getPromoList();
        List<DiscountCard> discountCards = productDAOFromFile.getCardsList();
        for (int i = 0; i < discountPromos.size(); i++) {
            System.out.println(discountPromos.get(i));
        }
        for (int i = 0; i < discountCards.size(); i++) {
            System.out.println(discountCards.get(i).getCardLevel().getDiscount());
        }
    }

}
