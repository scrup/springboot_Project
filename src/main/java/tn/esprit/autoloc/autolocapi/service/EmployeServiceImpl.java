package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IEmployeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {
    private final IEmployeRepository employeRepository;

    @Override
    public Employe create(Employe e) {
        if (e.getIdEmploye() != null) {
            throw new IllegalArgumentException("Un nouveau Employe ne doit pas avoir d'identifiant");
        }
        if (e.getNom() == null || e.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        if (e.getPrenom() == null || e.getPrenom().isBlank()) {
            throw new IllegalArgumentException("Le prénom est obligatoire");
        }
        if (e.getRole() == null) {
            throw new IllegalArgumentException("Le rôle est obligatoire");
        }
        return employeRepository.save(e);
    }

    @Override
    public Employe findById(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", id));
    }

    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @Override
    public Employe update(Long id, Employe e) {
        Employe existing = findById(id);
        if (e.getNom() == null || e.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        if (e.getPrenom() == null || e.getPrenom().isBlank()) {
            throw new IllegalArgumentException("Le prénom est obligatoire");
        }
        if (e.getRole() == null) {
            throw new IllegalArgumentException("Le rôle est obligatoire");
        }
        existing.setNom(e.getNom());
        existing.setPrenom(e.getPrenom());
        existing.setRole(e.getRole());
        return employeRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        if (!employeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employe", id);
        }
        employeRepository.deleteById(id);
    }
}

