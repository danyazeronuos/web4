package org.zero.web4.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.zero.web4.entity.Language;
import org.zero.web4.mapper.LanguageMapper;
import org.zero.web4.model.LanguageDTO;
import org.zero.web4.repository.LanguageRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class LanguageService {
    @Inject
    private LanguageRepository languageRepository;

    public List<Language> getAllLanguageList() throws SQLException {
        return languageRepository.getAllLanguageList();
    }

    public Language getLanguageById(Integer languageId) throws SQLException {
        return languageRepository.getLanguageById(languageId)
                .orElseThrow(() -> new IllegalArgumentException("Language entity with specified id not found"));
    }

    public void addLanguage(LanguageDTO language) throws SQLException {
        var mappedLanguage = LanguageMapper.map(language);
        languageRepository.addLanguage(mappedLanguage);
    }

    public void deleteLanguage(Integer languageId) throws SQLException {
        languageRepository.deleteLanguage(languageId);
    }

    public void updateLanguage(Integer languageId, LanguageDTO language) throws SQLException {
        var mappedLanguage = LanguageMapper.map(language);
        mappedLanguage.setId(languageId);

        languageRepository.updateLanguage(mappedLanguage);
    }
}
