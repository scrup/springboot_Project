package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Employe;

import java.util.List;

public interface IEmployeService {
    Employe create(Employe e);
    Employe findById(Long id);
    List<Employe> findAll();
    Employe update(Long id, Employe e);
    void deleteById(Long id);
}

