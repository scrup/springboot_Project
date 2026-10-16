package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IMaintenanceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {
    private final IMaintenanceRepository maintenanceRepository;

    @Override
    public Maintenance create(Maintenance e) {
        if (e.getIdMaintenance() != null) {
            throw new IllegalArgumentException("Un nouveau Maintenance ne doit pas avoir d'identifiant");
        }
        if (e.getDateDebut() == null) {
            throw new IllegalArgumentException("La date de début est obligatoire");
        }
        if (e.getDateFin() == null) {
            throw new IllegalArgumentException("La date de fin est obligatoire");
        }
        if (e.getDateFin().isBefore(e.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin ne peut pas être avant la date de début");
        }
        if (e.getDescription() == null || e.getDescription().isBlank()) {
            throw new IllegalArgumentException("La description est obligatoire");
        }
        return maintenanceRepository.save(e);
    }

    @Override
    public Maintenance findById(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance", id));
    }

    @Override
    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance update(Long id, Maintenance e) {
        Maintenance existing = findById(id);
        if (e.getDateDebut() == null) {
            throw new IllegalArgumentException("La date de début est obligatoire");
        }
        if (e.getDateFin() == null) {
            throw new IllegalArgumentException("La date de fin est obligatoire");
        }
        if (e.getDateFin().isBefore(e.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin ne peut pas être avant la date de début");
        }
        if (e.getDescription() == null || e.getDescription().isBlank()) {
            throw new IllegalArgumentException("La description est obligatoire");
        }
        existing.setDateDebut(e.getDateDebut());
        existing.setDateFin(e.getDateFin());
        existing.setDescription(e.getDescription());
        return maintenanceRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Maintenance", id);
        }
        maintenanceRepository.deleteById(id);
    }
}

