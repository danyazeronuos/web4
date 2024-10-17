package org.zero.web4.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zero.web4.entity.Resident;
import org.zero.web4.model.ResidentDTO;
import org.zero.web4.repository.CityRepository;
import org.zero.web4.repository.LanguageRepository;
import org.zero.web4.repository.ResidentRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class ResidentService {
    @Inject
    private ResidentRepository residentRepository;
    @Inject
    private LanguageRepository languageRepository;
    @Inject
    private CityRepository cityRepository;
    private static final Logger log = LogManager.getLogger(ResidentService.class);

    public Resident getResidentById(Integer residentId) throws SQLException {
        return residentRepository.getResidentById(residentId);
    }

    public List<Resident> getAllResidentList() throws SQLException {
        return residentRepository.getAllResidentList();
    }

    public void addResident(ResidentDTO residentDTO) throws SQLException {
        var city = cityRepository.getCityById(residentDTO.cityId())
                .orElseThrow(() -> {
                    log.info("City Entity not found with id -> {}", residentDTO.cityId());
                    return new IllegalArgumentException("City Entity not found");
                });
        var language = languageRepository.getLanguageById(residentDTO.languageId())
                .orElseThrow(() -> {
                    log.info("Language Entity not found with id -> {}", residentDTO.languageId());
                    return new IllegalArgumentException("Language Entity not found");
                });

        var resident = Resident.builder().name(residentDTO.name())
                .city(city)
                .language(language)
                .build();
        residentRepository.addResident(resident);


    }

}
