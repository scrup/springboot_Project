package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation create(Reservation e);
    Reservation findById(Long id);
    List<Reservation> findAll();
    Reservation update(Long id, Reservation e);
    void deleteById(Long id);
}

