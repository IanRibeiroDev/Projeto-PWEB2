package br.edu.ifpb.pweb2.armants.service;

import br.edu.ifpb.pweb2.armants.model.concreto.Aluno;
import br.edu.ifpb.pweb2.armants.model.concreto.Empresa;
import br.edu.ifpb.pweb2.armants.model.concreto.EstagioEfetivado;
import br.edu.ifpb.pweb2.armants.repository.EstagioEfetivadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EstagioEfetivadoService implements Service<EstagioEfetivado, Integer> {
    @Autowired
    private EstagioEfetivadoRepository estagioEfetivadoRepository;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private AlunoService alunoService;

    @Override
    public List<EstagioEfetivado> findAll() {
        return estagioEfetivadoRepository.findAll();
    }

    @Override
    public EstagioEfetivado findById(Integer id) {
        EstagioEfetivado estagioEfetivado = null;
        Optional<EstagioEfetivado> optionalEstagioEfetivado = estagioEfetivadoRepository.findById(id);
        if (optionalEstagioEfetivado.isPresent()) {
            estagioEfetivado = optionalEstagioEfetivado.get();
        }
        return estagioEfetivado;
    }

    @Override
    public EstagioEfetivado save(EstagioEfetivado estagioEfetivado) {
        Empresa empresa = empresaService.findById(estagioEfetivado.getEmpresa().getId());
        estagioEfetivado.setEmpresa(empresa);

        estagioEfetivado = estagioEfetivadoRepository.save(estagioEfetivado);

        List<Aluno> estagiarios = estagioEfetivado.getEstagiarios();
        if(!estagiarios.isEmpty()) {
            estagiarios.replaceAll(estagiario -> alunoService.findById(estagiario.getId()));

            for(Aluno aluno : estagiarios) {
                aluno.setEstagio(estagioEfetivado);
                alunoService.save(aluno);
            }
        }

        return estagioEfetivadoRepository.save(estagioEfetivado);
    }

    public EstagioEfetivado findByIdWithEstagiarios(Integer id) {
        return estagioEfetivadoRepository.findByIdWithEstagiarios(id);
    }

    public EstagioEfetivado findByIdWithEmpresa(Integer id) {
        return estagioEfetivadoRepository.findByIdWithEmpresa(id);
    }

    public void deleteById(Integer id) { estagioEfetivadoRepository.deleteById(id); }
}
