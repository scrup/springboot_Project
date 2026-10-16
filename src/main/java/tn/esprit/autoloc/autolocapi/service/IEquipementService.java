package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Equipement;

import java.util.List;

public interface IEquipementService {
    Equipement create(Equipement e);
    Equipement findById(Long id);
    List<Equipement> findAll();
    Equipement update(Long id, Equipement e);
    void deleteById(Long id);
}

