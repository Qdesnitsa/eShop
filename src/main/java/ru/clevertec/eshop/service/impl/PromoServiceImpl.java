package ru.clevertec.eshop.service.impl;

import ru.clevertec.eshop.dao.DAOFactory;
import ru.clevertec.eshop.dao.FactoryProvider;
import ru.clevertec.eshop.dao.impl.file.PromoDAO;
import ru.clevertec.eshop.model.promo.Promo;
import ru.clevertec.eshop.service.PromoService;

import java.util.List;
import java.util.Optional;

public class PromoServiceImpl implements PromoService<Promo> {
    FactoryProvider factoryProvider = DAOFactory.getInstance();
    PromoDAO promoDAO = factoryProvider.getPromoDAO();


    @Override
    public List<Promo> findAll() {
        return null;
    }

    @Override
    public Optional<Promo> findByID(Long entityId) {
        return Optional.empty();
    }
}
