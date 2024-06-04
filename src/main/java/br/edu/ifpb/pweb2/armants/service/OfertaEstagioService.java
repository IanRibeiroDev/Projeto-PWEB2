package br.edu.ifpb.pweb2.armants.service;

import br.edu.ifpb.pweb2.armants.model.concreto.*;
import br.edu.ifpb.pweb2.armants.repository.OfertaEstagioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OfertaEstagioService implements Service<OfertaEstagio, Integer> {
    @Autowired
    private OfertaEstagioRepository ofertaEstagioRepository;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private CompetenciaService competenciaService;

    @Autowired
    @Lazy
    private CandidaturaService candidaturaService;

    @Override
    public List<OfertaEstagio> findAll() {
        return ofertaEstagioRepository.findAll();
    }

    @Override
    public OfertaEstagio findById(Integer id) {
        OfertaEstagio ofertaEstagio = null;
        Optional<OfertaEstagio> optionalOfertaEstagio = ofertaEstagioRepository.findById(id);
        if (optionalOfertaEstagio.isPresent()) {
            ofertaEstagio = optionalOfertaEstagio.get();
        }
        return ofertaEstagio;
    }

    @Override
    public OfertaEstagio save(OfertaEstagio ofertaEstagio) {
        Empresa empresa = empresaService.findById(ofertaEstagio.getEmpresa().getId());
        ofertaEstagio.setEmpresa(empresa);

        List<Competencia> competenciasObrigatorias = ofertaEstagio.getCompetenciasObrigatorias();
        if(!competenciasObrigatorias.isEmpty()) {
            competenciasObrigatorias.replaceAll(competencia -> competenciaService.findById(competencia.getId()));
        }

        List<Competencia> competenciasOpcionais = ofertaEstagio.getCompetenciasOpcionais();
        if(!competenciasOpcionais.isEmpty()) {
            competenciasOpcionais.replaceAll(competencia -> competenciaService.findById(competencia.getId()));
        }

        List<Candidatura> candidatos = ofertaEstagio.getCandidatos();
        if(!candidatos.isEmpty()) {
            candidatos.replaceAll(candidato -> candidaturaService.findById(candidato.getId()));
        }

        return ofertaEstagioRepository.save(ofertaEstagio);
    }

    public OfertaEstagio findByIdWithCompetenciasObrigatorias(Integer id) {
        return ofertaEstagioRepository.findByIdWithCompetenciasObrigatorias(id);
    }

    public OfertaEstagio findByIdWithCompetenciasOpcionais(Integer id) {
        return ofertaEstagioRepository.findByIdWithCompetenciasOpcionais(id);
    }

    public OfertaEstagio findByIdWithCandidatos(Integer id) {
        return ofertaEstagioRepository.findByIdWithCandidatos(id);
    }

    public OfertaEstagio findByIdWithEmpresa(Integer id) {
        return ofertaEstagioRepository.findByIdWithEmpresa(id);
    }

    public void deleteById(Integer id) { ofertaEstagioRepository.deleteById(id); }
}
