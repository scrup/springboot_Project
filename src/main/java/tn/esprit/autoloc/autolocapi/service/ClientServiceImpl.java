package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IClientRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {
    private final IClientRepository clientRepository;

    @Override
    public Client create(Client e) {
        if (e.getIdClient() != null) {
            throw new IllegalArgumentException("Un nouveau Client ne doit pas avoir d'identifiant");
        }
        if (e.getNom() == null || e.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        if (e.getPrenom() == null || e.getPrenom().isBlank()) {
            throw new IllegalArgumentException("Le prénom est obligatoire");
        }
        if (e.getEmail() == null || e.getEmail().isBlank()) {
            throw new IllegalArgumentException("L'email est obligatoire");
        }
        if (e.getTelephone() == null || e.getTelephone().isBlank()) {
            throw new IllegalArgumentException("Le téléphone est obligatoire");
        }
        if (e.getNumPermis() == null || e.getNumPermis().isBlank()) {
            throw new IllegalArgumentException("Le numéro de permis est obligatoire");
        }
        if (e.getDateInscription() == null) {
            throw new IllegalArgumentException("La date d'inscription est obligatoire");
        }
        return clientRepository.save(e);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client update(Long id, Client e) {
        Client existing = findById(id);
        if (e.getNom() == null || e.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        if (e.getPrenom() == null || e.getPrenom().isBlank()) {
            throw new IllegalArgumentException("Le prénom est obligatoire");
        }
        if (e.getEmail() == null || e.getEmail().isBlank()) {
            throw new IllegalArgumentException("L'email est obligatoire");
        }
        if (e.getTelephone() == null || e.getTelephone().isBlank()) {
            throw new IllegalArgumentException("Le téléphone est obligatoire");
        }
        if (e.getNumPermis() == null || e.getNumPermis().isBlank()) {
            throw new IllegalArgumentException("Le numéro de permis est obligatoire");
        }
        if (e.getDateInscription() == null) {
            throw new IllegalArgumentException("La date d'inscription est obligatoire");
        }
        existing.setNom(e.getNom());
        existing.setPrenom(e.getPrenom());
        existing.setEmail(e.getEmail());
        existing.setTelephone(e.getTelephone());
        existing.setNumPermis(e.getNumPermis());
        existing.setDateInscription(e.getDateInscription());
        return clientRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client", id);
        }
        clientRepository.deleteById(id);
    }
}

