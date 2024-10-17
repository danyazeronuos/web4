package org.zero.web4.mapper;

import org.zero.web4.entity.Language;
import org.zero.web4.model.LanguageDTO;

public class LanguageMapper {
    public static Language map(LanguageDTO languageDTO) {
        return Language.builder()
                .title(languageDTO.title())
                .build();
    }
}
