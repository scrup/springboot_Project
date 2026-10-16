package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {
    private final IAgenceRepository agenceRepository;

    @Override
    public Agence create(Agence e) {
        if (e.getIdAgence() != null) {
            throw new IllegalArgumentException("Un nouveau Agence ne doit pas avoir d'identifiant");
        }
        if (e.getNom() == null || e.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        if (e.getVille() == null || e.getVille().isBlank()) {
            throw new IllegalArgumentException("La ville est obligatoire");
        }
        if (e.getAdresse() == null || e.getAdresse().isBlank()) {
            throw new IllegalArgumentException("L'adresse est obligatoire");
        }
        if (e.getTelephone() == null || e.getTelephone().isBlank()) {
            throw new IllegalArgumentException("Le téléphone est obligatoire");
        }
        return agenceRepository.save(e);
    }

    @Override
    public Agence findById(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", id));
    }

    @Override
    public List<Agence> findAll() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence update(Long id, Agence e) {
        Agence existing = findById(id);
        if (e.getNom() == null || e.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        if (e.getVille() == null || e.getVille().isBlank()) {
            throw new IllegalArgumentException("La ville est obligatoire");
        }
        if (e.getAdresse() == null || e.getAdresse().isBlank()) {
            throw new IllegalArgumentException("L'adresse est obligatoire");
        }
        if (e.getTelephone() == null || e.getTelephone().isBlank()) {
            throw new IllegalArgumentException("Le téléphone est obligatoire");
        }
        existing.setNom(e.getNom());
        existing.setVille(e.getVille());
        existing.setAdresse(e.getAdresse());
        existing.setTelephone(e.getTelephone());
        return agenceRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        if (!agenceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agence", id);
        }
        agenceRepository.deleteById(id);
    }
}

