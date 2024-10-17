package org.zero.web4.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.zero.web4.config.DatabaseConfig;
import org.zero.web4.entity.Language;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class LanguageRepository {
    @Inject
    private DatabaseConfig database;

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

    public Language getLanguageById(Integer languageId) throws SQLException {
        var statement = database.getConnection()
                .prepareStatement("select * from language where id = ?");
        statement.setInt(1, languageId);

        var resultSet = statement.executeQuery();
        resultSet.next();

        var languageTitle = resultSet.getString(2);
        statement.close();

        return new Language(languageId, languageTitle);
    }

    public void updateLanguage(Language language) throws SQLException {
        var statement = database.getConnection()
                .prepareStatement("update language set title = ? where id = ?");
        statement.setString(1, language.getTitle());
        statement.setInt(2, language.getId());

        statement.executeUpdate();
        statement.close();
    }

    public void addLanguage(Language language) throws SQLException {
        var statement = database.getConnection()
                .prepareStatement("insert into language(title) values(?)");
        statement.setString(1, language.getTitle());

        statement.executeUpdate();
        statement.close();
    }

    public void deleteLanguage(Integer languageId) throws SQLException {
        var statement = database.getConnection()
                .prepareStatement("delete from language where id = ?");
        statement.setInt(1, languageId);

        statement.executeUpdate();
        statement.close();
    }
}
