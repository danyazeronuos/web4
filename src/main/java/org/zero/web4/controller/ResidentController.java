package org.zero.web4.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.zero.web4.entity.Resident;
import org.zero.web4.model.ResidentDTO;
import org.zero.web4.service.ResidentService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    @PUT
    @Consumes("application/json")
    public void addResident(ResidentDTO residentDTO) throws SQLException {
        residentService.addResident(residentDTO);
    }

    @PATCH
    @Path("/{residentId}")
    @Consumes("application/json")
    public void updateResident(@PathParam("residentId") Integer residentId, ResidentDTO residentDTO) throws SQLException {
        residentService.updateResident(residentId, residentDTO);
    }

    @DELETE
    @Path("/{residentId}")
    public void deleteResident(@PathParam("residentId") Integer residentId) throws SQLException {
        residentService.deleteResident(residentId);
    }
}
