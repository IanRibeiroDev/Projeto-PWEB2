package br.edu.ifpb.pweb2.armants.service;

import br.edu.ifpb.pweb2.armants.model.concreto.Coordenador;
import br.edu.ifpb.pweb2.armants.repository.CoordenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CoordenadorService implements Service<Coordenador, Integer> {
    @Autowired
    private CoordenadorRepository coordenadorRepository;

    @Override
    public List<Coordenador> findAll() {
        return coordenadorRepository.findAll();
    }

    @Override
    public Coordenador findById(Integer id) {
        Coordenador coordenador = null;
        Optional<Coordenador> optionalCoordenador = coordenadorRepository.findById(id);
        if (optionalCoordenador.isPresent()) {
            coordenador = optionalCoordenador.get();
        }
        return coordenador;
    }

    @Override
    public Coordenador save(Coordenador coordenador) {
        return coordenadorRepository.save(coordenador);
    }

    public void deleteById(Integer id) { coordenadorRepository.deleteById(id); }
}
