package ru.clevertec.eshop.dao.source.database;

import ru.clevertec.eshop.dao.construction.CheckConstructor;
import ru.clevertec.eshop.dao.construction.EntityConstructor;
import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.dao.source.CheckDAO;
import ru.clevertec.eshop.dto.CheckDTO;
import ru.clevertec.eshop.dto.mapper.CheckMapper;
import ru.clevertec.eshop.model.Check;
import ru.clevertec.eshop.model.product.Product;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class CheckDAODatabase implements CheckDAO<Optional<CheckDTO>> {
    private static final String SQL_ADD_CHECK =
            "INSERT INTO checks (date_time, shop_id) values(?,?)";
    private static final String SQL_FIND_CHECK_BY_ID =
            "SELECT * FROM checks WHERE id = ?";
    private static final String SQL_FIND_LAST_CHECK =
            "SELECT * FROM checks ORDER BY ID DESC LIMIT 1";
    private static final String SQL_ADD_CHECK_PRODUCT =
            "INSERT INTO checks_products (check_id, product_id, products_quantity) values(?,?,?)";
    EntityConstructor entityConstructor = new CheckConstructor();
    CheckMapper checkMapper = new CheckMapper();

    public boolean save(Check check) throws DAOException {
        boolean isCheckAdded = false;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_CHECK)) {
            statement.setTimestamp(1, Timestamp.valueOf(check.getDateTime()));
            statement.setLong(2, check.getShopId());
            int counter = statement.executeUpdate();
            if (counter != 0) {
                isCheckAdded = true;
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to add check to the database", e);
        }
        return isCheckAdded;
    }

    @Override
    public boolean save(Optional<CheckDTO> checkDTO) throws DAOException {
        Check check = new Check();
        if (checkDTO.isPresent()) {
            check = checkMapper.mapCheckDTOToCheck(checkDTO.get());
        }
        //LOGGER.info("Attempt to add check and product in 2 schemas to the database");

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_CHECK_PRODUCT)) {

            connection.setAutoCommit(false);

            boolean isCheckSaved = save(check);
            Long checkId = null;
            if (isCheckSaved) {
                Check checkSaved = (Check) findByLastId().get();
                checkId = checkSaved.getId();
            }
            int counter = 0;
            for (Product product : checkDTO.get().getProducts()) {
                statement.setLong(1, checkId);
                statement.setLong(2, product.getId());
                statement.setInt(3, product.getQuantity());
                counter += statement.executeUpdate();
            }
            if (counter != checkDTO.get().getProducts().size() && !isCheckSaved) {
                connection.rollback();
            }

            connection.commit();

        } catch (SQLException e) {
            throw new DAOException("Failed attempt to add check to the database", e);
        }
        return true;
    }

    @Override
    public Optional<Check> findById(Long id) throws DAOException {
        Optional<Check> check = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement((SQL_FIND_CHECK_BY_ID))) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                check = Optional.ofNullable((Check) entityConstructor.constructEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find check by ID in the database", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return check;
    }

    @Override
    public Optional<Check> findByLastId() throws DAOException {
        Optional<Check> check = Optional.empty();
        ResultSet resultSet = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement((SQL_FIND_LAST_CHECK))) {
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                check = Optional.ofNullable((Check) entityConstructor.constructEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed attempt to find last check in the database", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Failed attempt to close resultSet", e);
            }
        }
        return check;
    }
}
