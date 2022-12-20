package ru.clevertec.eshop.dao.construction;

import ru.clevertec.eshop.model.Check;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

public class CheckConstructor implements EntityConstructor<Check> {
    @Override
    public Check constructEntity(Map map) {
        // TO DO add code here
        return null;
    }

    @Override
    public Check constructEntity(ResultSet resultSet) throws SQLException {
        Timestamp ts = resultSet.getTimestamp("date_time");
        LocalDateTime localDt =  LocalDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneOffset.UTC);
        return new Check.Builder()
                .setId(resultSet.getLong("id"))
                .setDateTime(localDt)
                .setShopId(resultSet.getInt("shop_id"))
                .build();
    }
}
