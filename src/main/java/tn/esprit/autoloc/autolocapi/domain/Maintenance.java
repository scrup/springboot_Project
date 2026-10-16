package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "maintenance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Long idMaintenance;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    LocalDate dateDebut;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    LocalDate dateFin;

    @Column(nullable = false, length = 500)
    @EqualsAndHashCode.Include
    @ToString.Include
    String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "vehicule_id")
    @ToString.Exclude
    private Vehicule vehicule;
}
