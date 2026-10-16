package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "paiement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Long idPaiement;

    @Column(nullable = false, precision = 10, scale = 2)
    @EqualsAndHashCode.Include
    @ToString.Include
    BigDecimal montant;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    LocalDate datePaiement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @EqualsAndHashCode.Include
    @ToString.Include
    ModePaiement modePaiement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("contrat-paiements")
    @ToString.Exclude
    private Contrat contrat;
}
