package ru.clevertec.eshop.dto.mapper;

import ru.clevertec.eshop.dto.CheckDTO;
import ru.clevertec.eshop.model.Check;

public class CheckMapper {
    public Check mapCheckDTOToCheck(CheckDTO checkDTO) {
        return new Check(checkDTO.getShopId(), checkDTO.getDateTime(), checkDTO.getProducts());
    }
}
