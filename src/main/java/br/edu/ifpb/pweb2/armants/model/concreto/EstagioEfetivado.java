package br.edu.ifpb.pweb2.armants.model.concreto;

import br.edu.ifpb.pweb2.armants.model.abstrato.Estagio;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class EstagioEfetivado extends Estagio {
    @NotNull(message = "Campo obrigatório!")
    @FutureOrPresent(message = "O estágio só poderá começar a partir da data atual!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate inicio;

    @NotNull(message = "Campo obrigatório!")
    @Future(message = "O estágio deve terminar em data futura!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate termino;

    @NotNull(message = "Um estágio efetivo deve conter alunos estagiários!")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estagio")
    @Column(nullable = false)
    private List<Aluno> estagiarios = new ArrayList<>();
}
