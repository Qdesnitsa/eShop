package ru.clevertec.eshop.service.impl;

import ru.clevertec.eshop.dao.DAOFactory;
import ru.clevertec.eshop.dao.DAOFactoryProvider;
import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.dao.source.PromoDAO;
import ru.clevertec.eshop.model.promo.Promo;
import ru.clevertec.eshop.service.PromoService;
import ru.clevertec.eshop.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class PromoServiceImpl implements PromoService<Promo> {
    private DAOFactoryProvider factoryProvider = DAOFactory.getInstance();
    private PromoDAO promoDAO = factoryProvider.getPromoDAO();

    @Override
    public List<Promo> findAll() throws ServiceException {
        List<Promo> promoList;
        try {
            promoList = promoDAO.findAll();
        } catch (DAOException e) {
            //LOGGER.error(e);
            throw new ServiceException("Failed attempt to find all promos in the database");
        }
        return promoList;
    }

    @Override
    public Optional<Promo> findByID(Long entityId) {
        // TODO add code here

        return Optional.empty();
    }
}
