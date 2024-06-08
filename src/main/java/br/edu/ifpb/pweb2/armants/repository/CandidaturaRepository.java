package br.edu.ifpb.pweb2.armants.repository;

import br.edu.ifpb.pweb2.armants.model.concreto.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Integer> {
    @Query(value = "from Candidatura ca left join fetch ca.candidato co where ca.id = :id")
    Candidatura findByIdWithCandidato(Integer id);

    @Query(value = "from Candidatura ca left join fetch ca.ofertaEstagio o left join fetch o.empresa e where ca.id = :id")
    Candidatura findByIdWithOfertaEstagio(Integer id);

    @Query(value = "from Candidatura ca join fetch ca.ofertaEstagio o join fetch " +
            "ca.candidato co WHERE co.id = :idCandidato AND o.id = :idOferta")
    Candidatura findByCandidatoAndOfertaEstagio(Integer idCandidato, Integer idOferta);

    List<Candidatura> findByOfertaEstagio_Id(Integer id);
}
