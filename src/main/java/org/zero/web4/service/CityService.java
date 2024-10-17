package org.zero.web4.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.zero.web4.entity.City;
import org.zero.web4.mapper.CityMapper;
import org.zero.web4.model.CityDTO;
import org.zero.web4.repository.CityRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class CityService {
    @Inject
    private CityRepository cityRepository;

    public List<City> getAllCityList() throws SQLException {
        return cityRepository.getAllCityList();
    }

    public City getCityById(Integer cityId) throws SQLException {
        return cityRepository.getCityById(cityId)
                .orElseThrow(() -> new IllegalArgumentException("City entity with specified id not found"));
    }

    public void addCity(CityDTO city) throws SQLException {
        var mappedCity = CityMapper.map(city);

        cityRepository.addCity(mappedCity);
    }

    public void deleteCity(Integer cityId) throws SQLException {
        cityRepository.deleteCity(cityId);
    }

    public void updateCity(Integer cityId, CityDTO city) throws SQLException {
        var mappedCity = CityMapper.map(city);
        mappedCity.setId(cityId);

        cityRepository.updateCity(mappedCity);
    }
}
