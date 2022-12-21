package ru.clevertec.eshop.util.parsing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataParser {
    private static final String SIGNS_TO_REPLACE = "(;|:|=|,|\\s)+";
    private static final String INPUT_SIGNS_TO_REPLACE = "-";
    private static final String NEW_DELIMETER = " ";

    public static String[] parseLine(String line) {
        String[] parameters = line.replaceAll(SIGNS_TO_REPLACE, NEW_DELIMETER).split(NEW_DELIMETER);
        return parameters;
    }

    public static Map<String, Object> obtainMap(String line) {
        String[] parameters = parseLine(line);
        Map<String, Object> paramsValues = new HashMap<>();
        for (int i = 1; i < parameters.length; i += 2) {
            paramsValues.put(parameters[i], parameters[i + 1]);
        }
        return paramsValues;
    }

    public static List<String> parseLine(String[] args) {
        List<String> formattedArgs = Arrays.stream(args)
                .map(elem -> elem.replace(INPUT_SIGNS_TO_REPLACE, NEW_DELIMETER))
                .flatMap(s -> Arrays.stream(s.split(NEW_DELIMETER)))
                .collect(Collectors.toList());
        return formattedArgs;
    }
}
