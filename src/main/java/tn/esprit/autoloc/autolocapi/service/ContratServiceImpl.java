package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IContratRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {
    private final IContratRepository contratRepository;

    @Override
    public Contrat create(Contrat e) {
        if (e.getIdContrat() != null) {
            throw new IllegalArgumentException("Un nouveau Contrat ne doit pas avoir d'identifiant");
        }
        if (e.getDateSignature() == null) {
            throw new IllegalArgumentException("La date de signature est obligatoire");
        }
        if (e.getMontantTotal() == null || e.getMontantTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le montant total ne doit pas être négatif");
        }
        if (e.getValide() == null) {
            throw new IllegalArgumentException("Le statut valide est obligatoire");
        }
        return contratRepository.save(e);
    }

    @Override
    public Contrat findById(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
    }

    @Override
    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat update(Long id, Contrat e) {
        Contrat existing = findById(id);
        if (e.getDateSignature() == null) {
            throw new IllegalArgumentException("La date de signature est obligatoire");
        }
        if (e.getMontantTotal() == null || e.getMontantTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le montant total ne doit pas être négatif");
        }
        if (e.getValide() == null) {
            throw new IllegalArgumentException("Le statut valide est obligatoire");
        }
        existing.setDateSignature(e.getDateSignature());
        existing.setMontantTotal(e.getMontantTotal());
        existing.setValide(e.getValide());
        return contratRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat", id);
        }
        contratRepository.deleteById(id);
    }
}

