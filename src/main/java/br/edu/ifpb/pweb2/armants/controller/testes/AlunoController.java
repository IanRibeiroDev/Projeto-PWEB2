package br.edu.ifpb.pweb2.armants.controller.testes;

import br.edu.ifpb.pweb2.armants.model.concreto.Aluno;
import br.edu.ifpb.pweb2.armants.model.concreto.Competencia;
import br.edu.ifpb.pweb2.armants.model.concreto.Coordenador;
import br.edu.ifpb.pweb2.armants.service.AlunoService;
import br.edu.ifpb.pweb2.armants.service.CompetenciaService;
import br.edu.ifpb.pweb2.armants.service.CoordenadorService;
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
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoService service;

    @Autowired
    private CompetenciaService competenciaService;

    @GetMapping
    public ModelAndView form(ModelAndView m) {
        m.addObject("aluno", new Aluno());
        m.addObject("competencias", competenciaService.findAll());
        m.setViewName("testes/aluno/form");
        return m;
    }

    @PostMapping
    public ModelAndView save(@Valid Aluno aluno, BindingResult result, ModelAndView modelAndView, RedirectAttributes attr) {
        if(result.hasErrors()) {
            modelAndView.setViewName("testes/aluno/form");
            modelAndView.addObject("aluno", aluno);
            modelAndView.addObject("competencias", competenciaService.findAll());
            return modelAndView;
        }

        String operacao = (aluno.getId() == null) ? "criado" : "salvo";
        service.save(aluno);
        modelAndView.setViewName("redirect:/alunos/list");
        attr.addFlashAttribute("mensagem", "Aluno " + operacao + " com sucesso!");
        return modelAndView;
    }

    @RequestMapping("/list")
    public ModelAndView list(ModelAndView m) {
        m.setViewName("testes/aluno/list");
        m.addObject("alunos", service.findAll());
        return m;
    }

    @RequestMapping("/{id}")
    public String getAlunoById(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("aluno", service.findById(id));
        model.addAttribute("competencias", competenciaService.findAll());
        return "testes/aluno/form";
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Integer id,
                                   ModelAndView mav, RedirectAttributes attr) {
        service.deleteById(id);
        attr.addFlashAttribute("mensagem", "Aluno removido com sucesso!");
        mav.setViewName("redirect:/alunos/list");
        return mav;
    }
}
