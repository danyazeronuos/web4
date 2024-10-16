package org.zero.web4.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.zero.web4.config.DatabaseConfig;
import org.zero.web4.entity.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CityRepository {
    @Inject
    private DatabaseConfig database;

    public List<City> getAllCityList() throws SQLException {
        var statement = database.getConnection().createStatement();
        var resultSet = statement.executeQuery("select * from city");

        var cityList = new ArrayList<City>();

        while (resultSet.next()) {
            var city = getCityFromResultSet(resultSet);
            cityList.add(city);
        }
        statement.close();

        return cityList;
    }

    public City getCityById(Integer cityId) throws SQLException {
        var statement = database.getConnection()
                .prepareStatement("select * from city where id = ?");
        statement.setInt(1, cityId);

        var resultSet = statement.executeQuery();
        resultSet.next();

        var city = getCityFromResultSet(resultSet);
        statement.close();

        return city;
    }

    public static City getCityFromResultSet(ResultSet resultSet) throws SQLException {
        var cityId = resultSet.getInt(1);
        var cityName = resultSet.getString(2);
        var cityFoundationYear = resultSet.getShort(3);
        var cityArea = resultSet.getShort(4);

        return new City(cityId, cityName, cityFoundationYear, cityArea);
    }
}
