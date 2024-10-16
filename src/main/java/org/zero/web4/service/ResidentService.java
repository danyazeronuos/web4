package org.zero.web4.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.zero.web4.entity.Resident;
import org.zero.web4.repository.ResidentRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class ResidentService {
    @Inject
    private ResidentRepository residentRepository;

    public Resident getResidentById(Integer residentId) throws SQLException {
        return residentRepository.getResidentById(residentId);
    }

    public List<Resident> getAllResidentList() throws SQLException {
        return residentRepository.getAllResidentList();
    }

}
