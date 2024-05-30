package br.edu.ifpb.pweb2.armants.model.abstrato;

import br.edu.ifpb.pweb2.armants.model.concreto.Empresa;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class Estagio extends EntidadeSerializada {
    @NotBlank(message = "Campo obrigatório!")
    @Size(max = 255, message = "Máximo de 255 caracteres!")
    @Column(nullable = false)
    private String atividadePrincipal;

    @NotNull(message = "Campo obrigatório!")
    //@IsNumber(message = "Campo deve conter apenas números!")
    @Min(value = 10, message = "Carga horária mínima de 10 horas semanais!")
    @Max(value = 30, message = "Carga horária máxima de 30 horas semanais!")
    private Integer CH;

    private Double salario;

    private Double valeTransporte;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
}
