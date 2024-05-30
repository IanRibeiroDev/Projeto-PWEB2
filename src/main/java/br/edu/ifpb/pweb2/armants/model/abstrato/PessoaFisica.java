package br.edu.ifpb.pweb2.armants.model.abstrato;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class PessoaFisica extends Usuario {
    @NotBlank(message = "Campo obrigatório!")
    @Size(max = 20, message = "Máximo de 20 caracteres!")
    //@IsAlpha(message = "Campo deve conter apenas letras!")
    @Column(length = 20, nullable = false)
    private String nome;

    @NotBlank(message = "Campo obrigatório!")
    @Size(max = 40, message = "Máximo de 40 caracteres!")
    //@IsAlpha(message = "Campo deve conter apenas letras!")
    @Column(length = 40, nullable = false)
    private String sobrenome;

    @NotBlank(message = "Campo obrigatório!")
    //@IsNumber(message = "Campo deve conter apenas números!")
    //@ValidarCpf(message = "Informe um CPF válido!")
    @Column(unique = true, length = 11, nullable = false)
    private String cpf;

    @NotNull(message = "Campo obrigatório!")
    @Past(message = "Informe uma data válida!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate dataNascimento;
}
