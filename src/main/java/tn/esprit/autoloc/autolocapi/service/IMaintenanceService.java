package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {
    Maintenance create(Maintenance e);
    Maintenance findById(Long id);
    List<Maintenance> findAll();
    Maintenance update(Long id, Maintenance e);
    void deleteById(Long id);
}

