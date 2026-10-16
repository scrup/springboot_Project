package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Client;

import java.util.List;

public interface IClientService {
    Client create(Client e);
    Client findById(Long id);
    List<Client> findAll();
    Client update(Long id, Client e);
    void deleteById(Long id);
}

