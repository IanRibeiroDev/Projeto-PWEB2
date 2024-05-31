package br.edu.ifpb.pweb2.armants.repository;

import br.edu.ifpb.pweb2.armants.model.concreto.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador, Integer> {
}
