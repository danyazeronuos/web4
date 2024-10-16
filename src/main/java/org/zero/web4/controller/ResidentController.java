package org.zero.web4.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import org.zero.web4.entity.Resident;
import org.zero.web4.service.ResidentService;

import java.sql.SQLException;
import java.util.List;

@Path("/resident")
@ApplicationScoped
public class ResidentController {
    @Inject
    private ResidentService residentService;

    @GET
    @Path("/{residentId}")
    @Produces("application/json")
    public Resident getResidentById(@PathParam("residentId") Integer residentId) throws SQLException {
        return residentService.getResidentById(residentId);
    }

    @GET
    @Produces("application/json")
    public List<Resident> getResidentById() throws SQLException {
        return residentService.getAllResidentList();
    }
}
