package org.zero.web4.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zero.web4.entity.Resident;
import org.zero.web4.model.ResidentDTO;
import org.zero.web4.repository.CityRepository;
import org.zero.web4.repository.LanguageRepository;
import org.zero.web4.repository.ResidentRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
        return residentRepository.getResidentById(residentId)
                .orElseThrow(() -> {
                    log.warn("Entity with this id -> {} does not exist!", residentId);
                    return new IllegalArgumentException("Entity with this id does not exist!");
                });
    }

    public List<Resident> getAllResidentList() throws SQLException {
        return residentRepository.getAllResidentList();
    }

    public void deleteResident(Integer residentId) throws SQLException {
        residentRepository.deleteResident(residentId);
    }

    public void addResident(ResidentDTO residentDTO) throws SQLException {
        var resident = this.getResidentWithFilledCityAndLanguage(residentDTO);

        residentRepository.addResident(resident);
    }

    public void updateResident(Integer residentId, ResidentDTO residentDTO) throws SQLException {
        var resident = this.getResidentWithFilledCityAndLanguage(residentDTO);
        resident.setId(residentId);

        residentRepository.updateResident(resident);
    }

    private Resident getResidentWithFilledCityAndLanguage(ResidentDTO residentDTO) throws SQLException {
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

        return Resident.builder().name(residentDTO.name())
                .city(city)
                .language(language)
                .build();
    }

}
