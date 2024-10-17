package org.zero.web4.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.util.PSQLException;
import org.zero.web4.config.DatabaseConfig;
import org.zero.web4.entity.City;
import org.zero.web4.entity.Language;
import org.zero.web4.entity.Resident;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ResidentRepository {
    @Inject
    private DatabaseConfig database;
    private static final Logger log = LogManager.getLogger(ResidentRepository.class);

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
        log.info("Successfully extracted {} City entities", residentList.size());
        statement.close();

        return residentList;
    }

    public Optional<Resident> getResidentById(Integer residentId) throws SQLException {
        var sql = """
                select
                    *
                from
                    resident r,
                    city c,
                    language l
                where r.id = ? and c.id = r.city and l.id = r.language
                """;
        var statement = database.getConnection().prepareStatement(sql);

        try (statement) {
            statement.setInt(1, residentId);
            var resultSet = statement.executeQuery();
            resultSet.next();
            return Optional.of(getResidentFromResultSet(resultSet));
        } catch (PSQLException exception) {
            log.info("Nothing found for the specified resident id");
            return Optional.empty();
        }
    }

    public void addResident(Resident resident) throws SQLException {
        var statement = database.getConnection()
                .prepareStatement("insert into resident(name, city, language) values(?, ?, ?)");
        statement.setString(1, resident.getName());
        statement.setInt(2, resident.getCity().getId());
        statement.setInt(3, resident.getLanguage().getId());

        statement.executeUpdate();
        log.info("Successfully added Resident entity");
        statement.close();

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
