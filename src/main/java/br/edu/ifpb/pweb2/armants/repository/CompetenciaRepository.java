package br.edu.ifpb.pweb2.armants.repository;

import br.edu.ifpb.pweb2.armants.model.concreto.Competencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenciaRepository extends JpaRepository<Competencia, Integer> {
    Competencia findByNome(String nome);
}
