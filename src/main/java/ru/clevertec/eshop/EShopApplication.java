package ru.clevertec.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.clevertec.eshop.controller.console.InputFromConsole;
import ru.clevertec.eshop.service.exception.ServiceException;

import java.util.Map;

@SpringBootApplication
public class EShopApplication {

    public static void main(String[] args) throws ServiceException {
        args = new String[]{"1-1", "2-2", "2-2", "card-1234", "5-y", "yy-7"};
        SpringApplication.run(EShopApplication.class, args);
        InputFromConsole inputFromConsole = new InputFromConsole();
        Map<Object, Integer> map = inputFromConsole.obtainCriteriaMap(args);
        for (Map.Entry<Object, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

}
