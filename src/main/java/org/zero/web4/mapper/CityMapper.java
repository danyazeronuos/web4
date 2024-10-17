package org.zero.web4.mapper;

import org.zero.web4.entity.City;
import org.zero.web4.model.CityDTO;

public class CityMapper {
    public static City map(CityDTO cityDTO) {
        return City.builder()
                .name(cityDTO.name())
                .foundationYear(cityDTO.foundationYear())
                .area(cityDTO.area())
                .build();
    }
}
