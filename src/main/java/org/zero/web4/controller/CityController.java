package org.zero.web4.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.zero.web4.entity.City;
import org.zero.web4.model.CityDTO;
import org.zero.web4.model.Identification;
import org.zero.web4.service.CityService;

import java.sql.SQLException;
import java.util.List;

@Path("/city")
@ApplicationScoped
public class CityController {
    @Inject
    private CityService cityService;

    @GET
    @Produces("application/json")
    public List<City> hello() throws SQLException {
        return cityService.getAllCityList();
    }

    @GET
    @Path("/{cityId}")
    @Produces("application/json")
    public City getCityById(@PathParam("cityId") Integer cityId) throws SQLException {
        return cityService.getCityById(cityId);
    }

    @DELETE
    @Path("/{cityId}")
    @Consumes("application/json")
    public void deleteCity(@PathParam("cityId") Integer cityId) throws SQLException {
        cityService.deleteCity(cityId);
    }

    @PUT
    @Consumes("application/json")
    public void addCity(CityDTO city) throws SQLException {
        cityService.addCity(city);
    }
}