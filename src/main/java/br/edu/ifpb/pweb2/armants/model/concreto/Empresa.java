package br.edu.ifpb.pweb2.armants.model.concreto;

import br.edu.ifpb.pweb2.armants.model.abstrato.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;
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
public class Empresa extends Usuario {
    @NotBlank(message = "Campo obrigatório!")
    @Size(max = 128, message = "Máximo de 128 caracteres!")
    @Column(length = 128, nullable = false)
    private String nome;

    @NotBlank(message = "Campo obrigatório!")
    @Size(min = 14, max = 14, message = "Digite um CNPJ válido!")
    //@IsNumber(message = "Campo deve conter apenas números!")
    //@ValidarCnpj(message = "Informe um CNPJ válido!")
    @Column(unique = true, length = 14, nullable = false)
    private String cnpj;

    @NotBlank(message = "Campo obrigatório!")
    @Size(max = 255, message = "Máximo de 255 caracteres!")
    @Column(nullable = false)
    private String endereco;

    @NotBlank(message = "Campo obrigatório!")
    @Size(max = 60, message = "Máximo de 60 caracteres!")
    //@IsAlpha(message = "Campo deve conter apenas letras!")
    @Column(length = 60, nullable = false)
    private String pessoaContato;

    @NotBlank(message = "Campo obrigatório!")
    @Size(max = 255, message = "Máximo de 255 caracteres!")
    @Column(nullable = false)
    private String atividadePrincipal;

    @NotBlank(message = "Campo obrigatório!")
    @Size(max = 255, message = "Máximo de 255 caracteres!")
    @Column(nullable = false)
    private String url;

    @NotNull(message = "Campo obrigatório!")
    @AssertFalse(message = "Empresa não pode estar bloqueada ao se cadastrar!")
    @Column(nullable = false)
    private Boolean bloqueada = false;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "empresa")
    private List<OfertaEstagio> ofertasCadastradas = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "empresa")
    private List<EstagioEfetivado> estagiosEfetivados = new ArrayList<>();

    //@Pdf
    private String comprovanteEndereco;
}
