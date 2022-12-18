package ru.clevertec.eshop.service.validation;

import ru.clevertec.eshop.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class DataValidator {
    private static final String INTEGER_VALUE = "^[\\d]+$";
    private static final String CARD = "card";

    public static List<String> obtainAvailableKeyValuesPattern() {
        List<String> keyPatterns = new ArrayList();
        keyPatterns.add(INTEGER_VALUE);
        keyPatterns.add(CARD);
        return keyPatterns;
    }

    public static List<String> validateCriteria(List<String> parsedData) throws ServiceException {
        List<String> validatedList = new ArrayList<>();
        List<String> failedValidatedElementsList = new ArrayList<>();
        List<String> patterns = obtainAvailableKeyValuesPattern();

        for (int j = 0; j < parsedData.size(); j += 2) {
            boolean oneMatch = false;
            for (int i = 0; i < patterns.size(); i++) {
                if (parsedData.get(j).matches(patterns.get(i)) &&
                        parsedData.get(j + 1).matches(INTEGER_VALUE)) {
                    validatedList.add(parsedData.get(j));
                    validatedList.add(parsedData.get(j + 1));
                    oneMatch = true;
                }
            }
            if (!oneMatch) {
                failedValidatedElementsList.add(parsedData.get(j));
                failedValidatedElementsList.add(parsedData.get(j + 1));
            }
        }
        if (!failedValidatedElementsList.isEmpty()) {
            throw new ServiceException("Invalid input of elements" +
                    failedValidatedElementsList + " + Please check it and try again");
        }
        return validatedList;
    }
}
