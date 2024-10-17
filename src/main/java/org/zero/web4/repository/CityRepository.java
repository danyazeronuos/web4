package org.zero.web4.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.util.PSQLException;
import org.zero.web4.config.DatabaseConfig;
import org.zero.web4.entity.City;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CityRepository {
    @Inject
    private DatabaseConfig database;
    private static final Logger log = LogManager.getLogger(CityRepository.class);

    public List<City> getAllCityList() throws SQLException {
        var statement = database.getConnection().createStatement();
        var resultSet = statement.executeQuery("select * from city");

        var cityList = new ArrayList<City>();

        while (resultSet.next()) {
            var city = getCityFromResultSet(resultSet);
            cityList.add(city);
        }
        log.info("Successfully extracted {} City entities", cityList.size());
        statement.close();

        return cityList;
    }

    public Optional<City> getCityById(Integer cityId) throws SQLException {
        var statement = database.getConnection()
                .prepareStatement("select * from city where id = ?");

        try (statement) {
            statement.setInt(1, cityId);
            var resultSet = statement.executeQuery();

            resultSet.next();
            var city = getCityFromResultSet(resultSet);
            log.info("City with id -> {} successfully extracted", cityId);

            return Optional.of(city);
        } catch (PSQLException e) {
            log.info("Nothing found for the specified city");
            return Optional.empty();
        }
    }

    public void addCity(City city) throws SQLException {
        var statement = database.getConnection()
                .prepareStatement("insert into city(name, foundation_year, area) values(?, ?, ?)");
        statement.setString(1, city.getName());
        statement.setShort(2, city.getFoundationYear());
        statement.setShort(3, city.getArea());

        statement.executeUpdate();
        log.info("Successfully added entity -> {}", city);
        statement.close();
    }

    public void updateCity(City city) throws SQLException {
        var statement = database.getConnection()
                .prepareStatement("update city set name = ?, foundation_year = ?, area = ? where id = ?");
        statement.setString(1, city.getName());
        statement.setShort(2, city.getFoundationYear());
        statement.setShort(3, city.getArea());
        statement.setInt(4, city.getId());

        statement.executeUpdate();
        log.info("Successfully updated entity -> {}", city);
        statement.close();
    }

    public void deleteCity(Integer cityId) throws SQLException {
        var statement = database.getConnection()
                .prepareStatement("delete from city where id = ?");
        statement.setInt(1, cityId);

        statement.executeUpdate();
        log.info("Successfully deleted entity with id -> {}", cityId);
        statement.close();
    }

    private static City getCityFromResultSet(ResultSet resultSet) throws SQLException {
        var cityId = resultSet.getInt(1);
        var cityName = resultSet.getString(2);
        var cityFoundationYear = resultSet.getShort(3);
        var cityArea = resultSet.getShort(4);

        return new City(cityId, cityName, cityFoundationYear, cityArea);
    }
}
