package br.edu.ifpb.pweb2.armants.service;

import br.edu.ifpb.pweb2.armants.model.concreto.Aluno;
import br.edu.ifpb.pweb2.armants.model.concreto.Competencia;
import br.edu.ifpb.pweb2.armants.model.concreto.EstagioEfetivado;
import br.edu.ifpb.pweb2.armants.model.concreto.OfertaEstagio;
import br.edu.ifpb.pweb2.armants.repository.AlunoRepository;
import br.edu.ifpb.pweb2.armants.repository.CompetenciaRepository;
import br.edu.ifpb.pweb2.armants.repository.EstagioEfetivadoRepository;
import br.edu.ifpb.pweb2.armants.repository.OfertaEstagioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AlunoService implements Service<Aluno, Integer> {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Autowired
    private OfertaEstagioRepository ofertaEstagioRepository;

    @Autowired
    private EstagioEfetivadoRepository estagioEfetivadoRepository;

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
            competencias.replaceAll(competencia -> competenciaRepository.findById(competencia.getId()).get());
        }

        List<OfertaEstagio> candidaturas = aluno.getCandidaturas();
        if(!candidaturas.isEmpty()) {
            candidaturas.replaceAll(ofertaEstagio -> ofertaEstagioRepository.findById(ofertaEstagio.getId()).get());
        }

        EstagioEfetivado estagio = aluno.getEstagio();
        if(estagio != null) {
            aluno.setEstagio(estagioEfetivadoRepository.findById(estagio.getId()).get());
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
