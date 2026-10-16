package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IVehiculeRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {
    private final IVehiculeRepository vehiculeRepository;

    @Override
    public Vehicule create(Vehicule e) {
        if (e.getIdVehicule() != null) {
            throw new IllegalArgumentException("Un nouveau Vehicule ne doit pas avoir d'identifiant");
        }
        if (e.getImmatriculation() == null || e.getImmatriculation().isBlank()) {
            throw new IllegalArgumentException("L'immatriculation est obligatoire");
        }
        if (e.getMarque() == null || e.getMarque().isBlank()) {
            throw new IllegalArgumentException("La marque est obligatoire");
        }
        if (e.getModele() == null || e.getModele().isBlank()) {
            throw new IllegalArgumentException("Le modèle est obligatoire");
        }
        if (e.getCategorie() == null) {
            throw new IllegalArgumentException("La catégorie est obligatoire");
        }
        if (e.getTarifJournalier() == null || e.getTarifJournalier().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le tarif journalier ne doit pas être négatif");
        }
        if (e.getStatut() == null) {
            throw new IllegalArgumentException("Le statut est obligatoire");
        }
        return vehiculeRepository.save(e);
    }

    @Override
    public Vehicule findById(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", id));
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule update(Long id, Vehicule e) {
        Vehicule existing = findById(id);
        if (e.getImmatriculation() == null || e.getImmatriculation().isBlank()) {
            throw new IllegalArgumentException("L'immatriculation est obligatoire");
        }
        if (e.getMarque() == null || e.getMarque().isBlank()) {
            throw new IllegalArgumentException("La marque est obligatoire");
        }
        if (e.getModele() == null || e.getModele().isBlank()) {
            throw new IllegalArgumentException("Le modèle est obligatoire");
        }
        if (e.getCategorie() == null) {
            throw new IllegalArgumentException("La catégorie est obligatoire");
        }
        if (e.getTarifJournalier() == null || e.getTarifJournalier().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le tarif journalier ne doit pas être négatif");
        }
        if (e.getStatut() == null) {
            throw new IllegalArgumentException("Le statut est obligatoire");
        }
        existing.setImmatriculation(e.getImmatriculation());
        existing.setMarque(e.getMarque());
        existing.setModele(e.getModele());
        existing.setCategorie(e.getCategorie());
        existing.setTarifJournalier(e.getTarifJournalier());
        existing.setStatut(e.getStatut());
        return vehiculeRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicule", id);
        }
        vehiculeRepository.deleteById(id);
    }
}

