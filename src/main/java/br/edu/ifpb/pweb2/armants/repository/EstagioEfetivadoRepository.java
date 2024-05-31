package br.edu.ifpb.pweb2.armants.repository;

import br.edu.ifpb.pweb2.armants.model.concreto.EstagioEfetivado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstagioEfetivadoRepository extends JpaRepository<EstagioEfetivado, Integer> {
    @Query(value = "from EstagioEfetivado ee left join fetch ee.estagiarios e where ee.id = :id")
    EstagioEfetivado findByIdWithEstagiarios(Integer id);

    @Query(value = "from EstagioEfetivado ee left join fetch ee.empresa e where ee.id = :id")
    EstagioEfetivado findByIdWithEmpresa(Integer id);
}
