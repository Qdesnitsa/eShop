package ru.clevertec.eshop.dao.source.file;

import ru.clevertec.eshop.dao.exception.DAOException;
import ru.clevertec.eshop.dao.source.CheckDAO;
import ru.clevertec.eshop.dto.CheckDTO;
import ru.clevertec.eshop.model.Check;
import ru.clevertec.eshop.service.CheckService;
import ru.clevertec.eshop.service.impl.CheckServiceImpl;
import ru.clevertec.eshop.util.AppConstant;

import java.io.*;
import java.util.Optional;

public class CheckDAOFromFile implements CheckDAO<Optional<CheckDTO>> {
    private final String filePath = AppConstant.CHECKS_FILE;

    @Override
    public boolean save(Optional<CheckDTO> checkDTO) {
        CheckService checkService = new CheckServiceImpl();
        String checkString = checkService.obtainFormattedFileCheck(checkDTO);
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(checkString);
            writer.append("\n\n\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Check file path to checks info. File is not fount", e);
        } catch (IOException e) {
            throw new RuntimeException("Error in getting data from file", e);
        }
        return true;
    }

    @Override
    public Optional<Check> findById(Long id) throws DAOException {
        // TODO find check from file by ID
        return Optional.empty();
    }

    @Override
    public Optional<Check> findByLastId() throws DAOException {
        // TODO find check from file with the biggest ID
        return Optional.empty();
    }
}
