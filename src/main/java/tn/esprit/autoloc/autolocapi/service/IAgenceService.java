package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Agence;

import java.util.List;

public interface IAgenceService {
    Agence create(Agence e);
    Agence findById(Long id);
    List<Agence> findAll();
    Agence update(Long id, Agence e);
    void deleteById(Long id);
}

