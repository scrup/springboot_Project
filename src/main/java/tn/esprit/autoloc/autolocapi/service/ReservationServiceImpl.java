package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IReservationRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {
    private final IReservationRepository reservationRepository;

    @Override
    public Reservation create(Reservation e) {
        if (e.getIdReservation() != null) {
            throw new IllegalArgumentException("Un nouveau Reservation ne doit pas avoir d'identifiant");
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
        if (e.getStatut() == null) {
            throw new IllegalArgumentException("Le statut est obligatoire");
        }
        return reservationRepository.save(e);
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", id));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation update(Long id, Reservation e) {
        Reservation existing = findById(id);
        if (e.getDateDebut() == null) {
            throw new IllegalArgumentException("La date de début est obligatoire");
        }
        if (e.getDateFin() == null) {
            throw new IllegalArgumentException("La date de fin est obligatoire");
        }
        if (e.getDateFin().isBefore(e.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin ne peut pas être avant la date de début");
        }
        if (e.getStatut() == null) {
            throw new IllegalArgumentException("Le statut est obligatoire");
        }
        existing.setDateDebut(e.getDateDebut());
        existing.setDateFin(e.getDateFin());
        existing.setStatut(e.getStatut());
        return reservationRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reservation", id);
        }
        reservationRepository.deleteById(id);
    }
}

