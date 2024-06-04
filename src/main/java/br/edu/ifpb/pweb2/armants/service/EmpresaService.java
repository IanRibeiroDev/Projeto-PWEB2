package br.edu.ifpb.pweb2.armants.service;

import br.edu.ifpb.pweb2.armants.model.concreto.Empresa;
import br.edu.ifpb.pweb2.armants.model.concreto.EstagioEfetivado;
import br.edu.ifpb.pweb2.armants.model.concreto.OfertaEstagio;
import br.edu.ifpb.pweb2.armants.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmpresaService implements Service<Empresa, Integer> {
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    @Lazy
    private OfertaEstagioService ofertaEstagioService;

    @Autowired
    @Lazy
    private EstagioEfetivadoService estagioEfetivadoService;

    @Override
    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    @Override
    public Empresa findById(Integer id) {
        Empresa empresa = null;
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
        if (optionalEmpresa.isPresent()) {
            empresa = optionalEmpresa.get();
        }
        return empresa;
    }

    @Override
    public Empresa save(Empresa empresa) {
        List<OfertaEstagio> ofertasCadastradas = empresa.getOfertasCadastradas();
        if(!ofertasCadastradas.isEmpty()) {
            ofertasCadastradas.replaceAll(ofertaCadastrada -> ofertaEstagioService.findById(ofertaCadastrada.getId()));
        }

        List<EstagioEfetivado> estagiosEfetivados = empresa.getEstagiosEfetivados();
        if(!estagiosEfetivados.isEmpty()) {
            estagiosEfetivados.replaceAll(estagioEfetivado -> estagioEfetivadoService.findById(estagioEfetivado.getId()));
        }

        return empresaRepository.save(empresa);
    }

    public Empresa findByIdWithOfertasCadastradas(Integer id) {
        return empresaRepository.findByIdWithOfertasCadastradas(id);
    }

    public Empresa findByIdWithEstagiosEfetivados(Integer id) {
        return empresaRepository.findByIdWithEstagiosEfetivados(id);
    }

    public void deleteById(Integer id) { empresaRepository.deleteById(id); }
}
