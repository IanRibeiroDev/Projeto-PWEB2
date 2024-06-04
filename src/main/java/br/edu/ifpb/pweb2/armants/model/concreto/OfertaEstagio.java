package br.edu.ifpb.pweb2.armants.model.concreto;

import br.edu.ifpb.pweb2.armants.model.abstrato.Estagio;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OfertaEstagio extends Estagio {
    @Size(max = 255, message = "Máximo de 255 caracteres!")
    private String preRequisitos;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "oferta_comp_obrigatoria",
            joinColumns = @JoinColumn(name = "id_oferta"),
            inverseJoinColumns = @JoinColumn(name = "id_competencia")
    )
    private List<Competencia> competenciasObrigatorias = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "oferta_comp_opcional",
            joinColumns = @JoinColumn(name = "id_oferta"),
            inverseJoinColumns = @JoinColumn(name = "id_competencia")
    )
    private List<Competencia> competenciasOpcionais = new ArrayList<>();

    @NotNull(message = "Campo obrigatório!")
    @AssertFalse(message = "Oferta de estágio não pode estar aprovada ao ser cadastrada!")
    @Column(nullable = false)
    private Boolean aprovada = false;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ofertaEstagio")
    private List<Candidatura> candidatos = new ArrayList<>();
}
