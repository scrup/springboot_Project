package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IEquipementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipementService {
    private final IEquipementRepository equipementRepository;

    @Override
    public Equipement create(Equipement e) {
        if (e.getIdEquipement() != null) {
            throw new IllegalArgumentException("Un nouveau Equipement ne doit pas avoir d'identifiant");
        }
        if (e.getLibelle() == null || e.getLibelle().isBlank()) {
            throw new IllegalArgumentException("Le libellé est obligatoire");
        }
        return equipementRepository.save(e);
    }

    @Override
    public Equipement findById(Long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", id));
    }

    @Override
    public List<Equipement> findAll() {
        return equipementRepository.findAll();
    }

    @Override
    public Equipement update(Long id, Equipement e) {
        Equipement existing = findById(id);
        if (e.getLibelle() == null || e.getLibelle().isBlank()) {
            throw new IllegalArgumentException("Le libellé est obligatoire");
        }
        existing.setLibelle(e.getLibelle());
        return equipementRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        if (!equipementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Equipement", id);
        }
        equipementRepository.deleteById(id);
    }
}

