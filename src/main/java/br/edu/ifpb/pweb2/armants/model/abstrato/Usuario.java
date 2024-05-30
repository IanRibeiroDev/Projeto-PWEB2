package br.edu.ifpb.pweb2.armants.model.abstrato;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class Usuario extends EntidadeSerializada {
    @NotBlank(message = "Campo obrigatório!")
    @Size(min = 10, max = 11, message = "Informe uma telefone fixo ou celular válido!")
    //@IsNumber(message = "Campo deve conter apenas números!")
    @Column(length = 11, nullable = false)
    private String telefone;

    @NotBlank(message = "Campo obrigatório!")
    @Email(message = "Informe um email válido!")
    @Size(max = 255, message = "Máximo de 255 caracteres!")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Campo obrigatório!")
    @Size(min = 8, max = 12)
    //@HasUpperCase(message = "Senha deve conter pelo menos uma letra maiúscula!")
    //@HasSpecialCharacter(message = "Senha deve conter pelo menos um caracter especial!")
    @Column(length = 12, nullable = false)
    private String senha;
}
