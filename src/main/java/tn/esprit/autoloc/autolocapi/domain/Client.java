package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Long idClient;

    @Column(nullable = false, length = 100)
    @EqualsAndHashCode.Include
    @ToString.Include
    String nom;

    @Column(nullable = false, length = 100)
    @EqualsAndHashCode.Include
    @ToString.Include
    String prenom;

    @Column(nullable = false, unique = true, length = 150)
    @EqualsAndHashCode.Include
    @ToString.Include
    String email;

    @Column(nullable = false, length = 20)
    @EqualsAndHashCode.Include
    @ToString.Include
    String telephone;

    @Column(nullable = false, length = 30)
    @EqualsAndHashCode.Include
    @ToString.Include
    String numPermis;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    LocalDate dateInscription;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    @ToString.Exclude
    private List<Reservation> reservations = new ArrayList<>();
}
