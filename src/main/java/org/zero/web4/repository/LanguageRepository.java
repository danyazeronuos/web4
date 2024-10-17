package org.zero.web4.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.util.PSQLException;
import org.zero.web4.config.DatabaseConfig;
import org.zero.web4.entity.Language;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class LanguageRepository {
    @Inject
    private DatabaseConfig database;
    private static final Logger log = LogManager.getLogger(LanguageRepository.class);

    public List<Language> getAllLanguageList() throws SQLException {
        Statement statement = database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from language");

        List<Language> languageList = new ArrayList<>();

        while (resultSet.next()) {
            var languageId = resultSet.getInt(1);
            var languageTitle = resultSet.getString(2);
            languageList.add(new Language(languageId, languageTitle));
        }
        statement.close();

        return languageList;
    }

    public Optional<Language> getLanguageById(Integer languageId) throws SQLException {
        var statement = database.getConnection()
                .prepareStatement("select * from language where id = ?");


        log.info("Request for extracting Language entity with id -> {}", languageId);
        try (statement) {

            statement.setInt(1, languageId);
            var resultSet = statement.executeQuery();
            resultSet.next();

            var languageTitle = resultSet.getString(2);
            log.info("Language with id -> {} successfully extracted", languageId);

            return Optional.of(new Language(languageId, languageTitle));
        } catch (PSQLException exception) {
            log.warn("Nothing was found for the specified language id. Exception -> {}", exception.getMessage());
            return Optional.empty();
        }

    }

    public void updateLanguage(Language language) throws SQLException {
        var statement = database.getConnection()
                .prepareStatement("update language set title = ? where id = ?");
        statement.setString(1, language.getTitle());
        statement.setInt(2, language.getId());

        statement.executeUpdate();
        log.info("Successfully updated entity -> {}", language);
        statement.close();
    }

    public void addLanguage(Language language) throws SQLException {
        var statement = database.getConnection()
                .prepareStatement("insert into language(title) values(?)");
        statement.setString(1, language.getTitle());

        statement.executeUpdate();
        log.info("Successfully added entity -> {}", language);
        statement.close();
    }

    public void deleteLanguage(Integer languageId) throws SQLException {
        var statement = database.getConnection()
                .prepareStatement("delete from language where id = ?");
        statement.setInt(1, languageId);

        statement.executeUpdate();
        log.info("Successfully deleted entity with id -> {}", languageId);
        statement.close();
    }
}
