package ru.clevertec.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.clevertec.eshop.controller.console.InputConsoleDataProcessor;
import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.service.exception.ServiceException;

import java.io.IOException;

@SpringBootApplication
public class EShopApplication {
    public static void main(String[] args) throws ServiceException, IOException{
        SpringApplication.run(EShopApplication.class, args);
        InputConsoleDataProcessor inputFromConsole = new InputConsoleDataProcessor();
        inputFromConsole.obtainCheck(args);
    }
}
