package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Contrat;

import java.util.List;

public interface IContratService {
    Contrat create(Contrat e);
    Contrat findById(Long id);
    List<Contrat> findAll();
    Contrat update(Long id, Contrat e);
    void deleteById(Long id);
}

