package tn.esprit.autoloc.autolocapi.domain;

import jakarta.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contrat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Long idContrat;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    LocalDate dateSignature;

    @Column(nullable = false, precision = 10, scale = 2)
    @EqualsAndHashCode.Include
    @ToString.Include
    BigDecimal montantTotal;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    Boolean valide;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    @ToString.Exclude
    private Reservation reservation;

    @OneToMany(mappedBy = "contrat", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("contrat-paiements")
    @ToString.Exclude
    private List<Paiement> paiements = new ArrayList<>();

    public void addPaiement(Paiement paiement) {
        if (paiement == null) {
            return;
        }
        paiements.add(paiement);
        paiement.setContrat(this);
    }

    public void removePaiement(Paiement paiement) {
        if (paiement == null) {
            return;
        }
        paiements.remove(paiement);
        paiement.setContrat(null);
    }
}
