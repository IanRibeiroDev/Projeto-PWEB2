package br.edu.ifpb.pweb2.armants.model.concreto;

import br.edu.ifpb.pweb2.armants.model.abstrato.EntidadeSerializada;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Candidatura extends EntidadeSerializada {
    @NotNull(message = "Uma candidatura deve estar atrelada a um aluno!")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aluno")
    private Aluno candidato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_oferta")
    private OfertaEstagio ofertaEstagio;

    @NotNull(message = "Uma candidatura deve conter status!")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.EM_ANALISE;

    public enum Status {
        ACEITA,
        NEGADA,
        EM_ANALISE
    }
}
