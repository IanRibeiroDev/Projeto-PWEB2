package br.edu.ifpb.pweb2.armants.controller.testes;

import br.edu.ifpb.pweb2.armants.model.concreto.EstagioEfetivado;
import br.edu.ifpb.pweb2.armants.model.concreto.OfertaEstagio;
import br.edu.ifpb.pweb2.armants.service.AlunoService;
import br.edu.ifpb.pweb2.armants.service.EmpresaService;
import br.edu.ifpb.pweb2.armants.service.EstagioEfetivadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/estagios")
public class EstagioEfetivadoController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private EstagioEfetivadoService estagioEfetivadoService;

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ModelAndView form(ModelAndView m) {
        m.addObject("estagio", new EstagioEfetivado());
        m.addObject("empresas", empresaService.findAll());
        m.addObject("alunos", alunoService.findAll());
        m.setViewName("testes/estagio_efetivado/form");
        return m;
    }

    @PostMapping
    public ModelAndView save(@Valid EstagioEfetivado estagioEfetivado, BindingResult result, ModelAndView m, RedirectAttributes attr) {
        if(result.hasErrors()) {
            m.setViewName("testes/estagio_efetivado/form");
            m.addObject("estagio", estagioEfetivado);
            m.addObject("empresas", empresaService.findAll());
            m.addObject("alunos", alunoService.findAll());
            return m;
        }

        String operacao = (estagioEfetivado.getId() == null) ? "criado" : "salvo";
        estagioEfetivadoService.save(estagioEfetivado);
        m.setViewName("redirect:/estagios/list");
        attr.addFlashAttribute("mensagem", "Estágio " + operacao + " com sucesso!");
        return m;
    }

    @RequestMapping("/list")
    public ModelAndView list(ModelAndView m) {
        m.setViewName("testes/estagio_efetivado/list");
        m.addObject("estagios", estagioEfetivadoService.findAll());
        return m;
    }

    @RequestMapping("/{id}")
    public String getOfertaById(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("estagio", estagioEfetivadoService.findById(id));
        model.addAttribute("empresas", empresaService.findAll());
        model.addAttribute("alunos", alunoService.findAll());
        return "testes/estagio_efetivado/form";
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Integer id,
                                   ModelAndView mav, RedirectAttributes attr) {
        estagioEfetivadoService.deleteById(id);
        attr.addFlashAttribute("mensagem", "Estágio removido com sucesso!");
        mav.setViewName("redirect:/estagios/list");
        return mav;
    }
}
