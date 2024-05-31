package br.edu.ifpb.pweb2.armants.repository;

import br.edu.ifpb.pweb2.armants.model.concreto.OfertaEstagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaEstagioRepository extends JpaRepository<OfertaEstagio, Integer> {
    @Query(value = "from OfertaEstagio oe left join fetch oe.competenciasObrigatorias c where oe.id = :id")
    OfertaEstagio findByIdWithCompetenciasObrigatorias(Integer id);

    @Query(value = "from OfertaEstagio oe left join fetch oe.competenciasOpcionais c where oe.id = :id")
    OfertaEstagio findByIdWithCompetenciasOpcionais(Integer id);

    @Query(value = "from OfertaEstagio oe left join fetch oe.candidatos c where oe.id = :id")
    OfertaEstagio findByIdWithCandidatos(Integer id);

    @Query(value = "from OfertaEstagio oe left join fetch oe.empresa e where oe.id = :id")
    OfertaEstagio findByIdWithEmpresa(Integer id);
}
