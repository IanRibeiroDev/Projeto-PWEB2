package br.edu.ifpb.pweb2.armants.model.concreto;

import br.edu.ifpb.pweb2.armants.model.abstrato.EntidadeSerializada;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Candidatura extends EntidadeSerializada {
    @NotNull(message = "Uma candidatura deve estar atrelada a um aluno!")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_aluno")
    private Aluno candidato;

    @NotNull(message = "Uma candidatura deve estar atrelada a uma oferta de estágio!")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_oferta")
    private OfertaEstagio ofertaEstagio;

    @NotNull(message = "Uma candidatura deve conter status!")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private enum Status {
        ACEITA,
        NEGADA,
        EM_ANALISE
    }
}
