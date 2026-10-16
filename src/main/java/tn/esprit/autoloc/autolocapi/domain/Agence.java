package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Long idAgence;

    @Column(nullable = false, length = 100)
    @EqualsAndHashCode.Include
    @ToString.Include
    String nom;

    @Column(nullable = false, length = 100)
    @EqualsAndHashCode.Include
    @ToString.Include
    String ville;

    @Column(nullable = false, length = 255)
    @EqualsAndHashCode.Include
    @ToString.Include
    String adresse;

    @Column(nullable = false, length = 20)
    @EqualsAndHashCode.Include
    @ToString.Include
    String telephone;

    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Vehicule> vehicules = new ArrayList<>();

    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Employe> employes = new ArrayList<>();
}
