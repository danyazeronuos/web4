package org.zero.web4.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.zero.web4.entity.Language;
import org.zero.web4.model.LanguageDTO;
import org.zero.web4.service.LanguageService;

import java.sql.SQLException;
import java.util.List;

@Path("/lang")
@ApplicationScoped
public class LanguageController {
    @Inject
    private LanguageService languageService;

    @GET
    @Produces("application/json")
    public List<Language> getAllLanguageList() throws SQLException {
        return languageService.getAllLanguageList();
    }

    @GET
    @Path("/{languageId}")
    @Produces("application/json")
    public Language getLanguageById(@PathParam("languageId") Integer languageId) throws SQLException {
        return languageService.getLanguageById(languageId);
    }

    @PUT
    @Consumes("application/json")
    public void addLanguage(LanguageDTO language) throws SQLException {
        languageService.addLanguage(language);
    }

    @DELETE
    @Path("/{languageId}")
    public void deleteLanguage(@PathParam("languageId") Integer languageId) throws SQLException {
        languageService.deleteLanguage(languageId);
    }


    @PATCH
    @Consumes
    @Path("/{languageId}")
    public void updateLanguage(@PathParam("languageId") Integer languageId, LanguageDTO languageDTO) throws SQLException {
        languageService.updateLanguage(languageId, languageDTO);
    }
}
