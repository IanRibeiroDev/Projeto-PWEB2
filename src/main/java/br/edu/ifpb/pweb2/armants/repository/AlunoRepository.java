package br.edu.ifpb.pweb2.armants.repository;

import br.edu.ifpb.pweb2.armants.model.concreto.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    @Query(value = "from Aluno a left join fetch a.competencias c where a.id = :id")
    Aluno findByIdWithCompetencias(Integer id);

    @Query(value = "from Aluno a left join fetch a.candidaturas c where a.id = :id")
    Aluno findByIdWithCandidaturas(Integer id);

    @Query(value = "from Aluno a left join fetch a.estagio e where a.id = :id")
    Aluno findByIdWithEstagio(Integer id);
}
