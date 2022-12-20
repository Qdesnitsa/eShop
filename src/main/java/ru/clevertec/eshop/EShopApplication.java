package ru.clevertec.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.clevertec.eshop.controller.console.InputDataProcessor;
import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.service.exception.ServiceException;

import java.io.IOException;

@SpringBootApplication
public class EShopApplication {
    public static void main(String[] args) throws ServiceException, IOException, DAOException {
        args = new String[]{"1-1", "2-2", "2-20", "card-5678"};
        SpringApplication.run(EShopApplication.class, args);
        InputDataProcessor inputFromConsole = new InputDataProcessor();
        inputFromConsole.obtainCheck(args);
    }
}
