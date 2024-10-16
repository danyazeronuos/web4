package org.zero.web4.service;

import jakarta.ejb.Singleton;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.zero.web4.config.DatabaseConfig;
import org.zero.web4.entity.City;
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
        return cityRepository.getCityById(cityId);
    }
}
