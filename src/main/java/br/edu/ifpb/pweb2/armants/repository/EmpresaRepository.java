package br.edu.ifpb.pweb2.armants.repository;

import br.edu.ifpb.pweb2.armants.model.concreto.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    @Query(value = "from Empresa e left join fetch e.ofertasCadastradas o where e.id = :id")
    Empresa findByIdWithOfertasCadastradas(Integer id);

    @Query(value = "from Empresa e left join fetch e.estagiosEfetivados es where e.id = :id")
    Empresa findByIdWithEstagiosEfetivados(Integer id);
}
