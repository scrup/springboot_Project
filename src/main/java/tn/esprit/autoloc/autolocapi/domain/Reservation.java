package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Long idReservation;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    LocalDate dateDebut;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @EqualsAndHashCode.Include
    @ToString.Include
    StatutReservation statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Vehicule vehicule;

    @OneToOne(mappedBy = "reservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Contrat contrat;
}
