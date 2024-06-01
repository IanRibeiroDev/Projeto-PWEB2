package br.edu.ifpb.pweb2.armants.service;

import br.edu.ifpb.pweb2.armants.model.concreto.Competencia;
import br.edu.ifpb.pweb2.armants.repository.CompetenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CompetenciaService implements Service<Competencia, Integer> {
    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Override
    public List<Competencia> findAll() {
        return competenciaRepository.findAll();
    }

    @Override
    public Competencia findById(Integer id) {
        Competencia competencia = null;
        Optional<Competencia> optionalCompetencia = competenciaRepository.findById(id);
        if (optionalCompetencia.isPresent()) {
            competencia = optionalCompetencia.get();
        }
        return competencia;
    }

    @Override
    public Competencia save(Competencia competencia) {
        return competenciaRepository.save(competencia);
    }

    public Competencia findByNome(String nome) {
        return competenciaRepository.findByNome(nome);
    }

    public void deleteById(Integer id) { competenciaRepository.deleteById(id); }
}
