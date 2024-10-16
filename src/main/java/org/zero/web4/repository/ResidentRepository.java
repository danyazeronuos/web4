package org.zero.web4.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.zero.web4.config.DatabaseConfig;
import org.zero.web4.entity.City;
import org.zero.web4.entity.Language;
import org.zero.web4.entity.Resident;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ResidentRepository {
    @Inject
    private DatabaseConfig database;

    public List<Resident> getAllResidentList() throws SQLException {

        var sql = """
                select
                    *
                from
                    resident r,
                    city c,
                    language l
                where c.id = r.city and l.id = r.language
                """;
        var statement = database.getConnection().createStatement();
        var resultSet = statement.executeQuery(sql);

        var residentList = new ArrayList<Resident>();

        while (resultSet.next()) {
            residentList.add(getResidentFromResultSet(resultSet));
        }
        statement.close();

        return residentList;
    }

    public Resident getResidentById(Integer residentId) throws SQLException {
        var sql = """
                select
                    *
                from
                    resident r,
                    city c,
                    language l
                where r.id = 2 and c.id = r.city and l.id = r.language
                """;
        var statement = database.getConnection().prepareStatement(sql);
        var resultSet = statement.executeQuery();
        resultSet.next();

        var resident = getResidentFromResultSet(resultSet);
        statement.close();

        return resident;
    }

    private static Resident getResidentFromResultSet(ResultSet resultSet) throws SQLException {
        var residentId = resultSet.getInt(1);
        var residentName = resultSet.getString(2);

        var cityId = resultSet.getInt(5);
        var cityName = resultSet.getString(6);
        var cityFoundationYear = resultSet.getShort(7);
        var cityArea = resultSet.getShort(8);
        var city = new City(cityId, cityName, cityFoundationYear, cityArea);

        var languageId = resultSet.getInt(9);
        var languageTitle = resultSet.getString(10);
        var language = new Language(languageId, languageTitle);

        return new Resident(residentId, residentName, city, language);
    }
}
