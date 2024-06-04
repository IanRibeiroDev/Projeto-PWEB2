package br.edu.ifpb.pweb2.armants.service;

import br.edu.ifpb.pweb2.armants.model.concreto.Aluno;
import br.edu.ifpb.pweb2.armants.model.concreto.Candidatura;
import br.edu.ifpb.pweb2.armants.model.concreto.OfertaEstagio;
import br.edu.ifpb.pweb2.armants.repository.CandidaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CandidaturaService implements Service<Candidatura, Integer> {
    @Autowired
    private CandidaturaRepository candidaturaRepository;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private OfertaEstagioService ofertaEstagioService;

    @Override
    public List<Candidatura> findAll() {
        return candidaturaRepository.findAll();
    }

    @Override
    public Candidatura findById(Integer id) {
        Candidatura candidatura = null;
        Optional<Candidatura> optionalCandidatura = candidaturaRepository.findById(id);
        if (optionalCandidatura.isPresent()) {
            candidatura = optionalCandidatura.get();
        }
        return candidatura;
    }

    @Override
    public Candidatura save(Candidatura candidatura) {
        Aluno candidato = alunoService.findById(candidatura.getCandidato().getId());
        candidatura.setCandidato(candidato);

        OfertaEstagio ofertaEstagio = ofertaEstagioService.findById(candidatura.getOfertaEstagio().getId());
        candidatura.setOfertaEstagio(ofertaEstagio);

        return candidaturaRepository.save(candidatura);
    }

    public Candidatura findByIdWithCandidato(Integer id) {
        return candidaturaRepository.findByIdWithCandidato(id);
    }

    public Candidatura findByIdWithOfertaEstagio(Integer id) {
        return candidaturaRepository.findByIdWithOfertaEstagio(id);
    }

    public void deleteById(Integer id) { candidaturaRepository.deleteById(id); }
}
