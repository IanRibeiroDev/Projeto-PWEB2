package br.edu.ifpb.pweb2.armants.service;

import br.edu.ifpb.pweb2.armants.model.concreto.*;
import br.edu.ifpb.pweb2.armants.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AlunoService implements Service<Aluno, Integer> {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CompetenciaService competenciaService;

    @Autowired
    @Lazy
    private CandidaturaService candidaturaService;

    @Autowired
    @Lazy
    private EstagioEfetivadoService estagioEfetivadoService;

    @Override
    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    @Override
    public Aluno findById(Integer id) {
        Aluno aluno = null;
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            aluno = optionalAluno.get();
        }
        return aluno;
    }

    @Override
    public Aluno save(Aluno aluno) {
        List<Competencia> competencias = aluno.getCompetencias();
        if(!competencias.isEmpty()) {
            competencias.replaceAll(competencia -> competenciaService.findById(competencia.getId()));
        }

        List<Candidatura> candidaturas = aluno.getCandidaturas();
        if(!candidaturas.isEmpty()) {
            candidaturas.replaceAll(ofertaEstagio -> candidaturaService.findById(ofertaEstagio.getId()));
        }

        EstagioEfetivado estagio = aluno.getEstagio();
        if(estagio != null) {
            aluno.setEstagio(estagioEfetivadoService.findById(estagio.getId()));
        }

        return alunoRepository.save(aluno);
    }

    public Aluno findByIdWithCompetencias(Integer id) {
        return alunoRepository.findByIdWithCompetencias(id);
    }

    public Aluno findByIdWithCandidaturas(Integer id){
        return alunoRepository.findByIdWithCandidaturas(id);
    }

    public Aluno findByIdWithEstagio(Integer id) {
        return alunoRepository.findByIdWithEstagio(id);
    }

    public void deleteById(Integer id) { alunoRepository.deleteById(id); }
}
