package org.zero.web4.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.zero.web4.config.DatabaseConfig;
import org.zero.web4.entity.Language;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class LanguageRepository {
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
}
