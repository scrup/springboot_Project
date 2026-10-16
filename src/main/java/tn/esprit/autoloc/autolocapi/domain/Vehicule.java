package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vehicule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Long idVehicule;

    @Column(nullable = false, unique = true, length = 20)
    @EqualsAndHashCode.Include
    @ToString.Include
    String immatriculation;

    @Column(nullable = false, length = 50)
    @EqualsAndHashCode.Include
    @ToString.Include
    String marque;

    @Column(nullable = false, length = 50)
    @EqualsAndHashCode.Include
    @ToString.Include
    String modele;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @EqualsAndHashCode.Include
    @ToString.Include
    CategorieVehicule categorie;

    @Column(nullable = false, precision = 10, scale = 2)
    @EqualsAndHashCode.Include
    @ToString.Include
    BigDecimal tarifJournalier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @EqualsAndHashCode.Include
    @ToString.Include
    StatutVehicule statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agence_id")
    @ToString.Exclude
    private Agence agence;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "vehicule_equipement",
            joinColumns = @JoinColumn(name = "vehicule_id"),
            inverseJoinColumns = @JoinColumn(name = "equipement_id")
    )
    @ToString.Exclude
    private Set<Equipement> equipements = new HashSet<>();

    @OneToMany(mappedBy = "vehicule", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Set<Maintenance> maintenances = new HashSet<>();
}