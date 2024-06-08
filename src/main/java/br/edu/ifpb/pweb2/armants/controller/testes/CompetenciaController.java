package br.edu.ifpb.pweb2.armants.controller.testes;

import br.edu.ifpb.pweb2.armants.model.concreto.Competencia;
import br.edu.ifpb.pweb2.armants.model.concreto.Coordenador;
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
@RequestMapping("/competencias")
public class CompetenciaController {
    @Autowired
    private CompetenciaService service;

    @GetMapping
    public ModelAndView form(ModelAndView m) {
        m.addObject("competencia", new Competencia());
        m.setViewName("testes/competencia/form");
        return m;
    }

    @PostMapping
    public ModelAndView save(@Valid Competencia competencia, BindingResult result, ModelAndView modelAndView, RedirectAttributes attr) {
        if(result.hasErrors()) {
            modelAndView.setViewName("testes/competencia/form");
            modelAndView.addObject("competencia", competencia);
            return modelAndView;
        }

        String operacao = (competencia.getId() == null) ? "criada" : "salva";
        service.save(competencia);
        modelAndView.setViewName("redirect:/competencias/list");
        attr.addFlashAttribute("mensagem", "Competência " + operacao + " com sucesso!");
        return modelAndView;
    }

    @RequestMapping("/list")
    public ModelAndView list(ModelAndView m) {
        m.setViewName("testes/competencia/list");
        m.addObject("competencias", service.findAll());
        return m;
    }

    @RequestMapping("/{id}")
    public String getCompetenciaById(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("competencia", service.findById(id));
        return "testes/competencia/form";
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Integer id,
                                   ModelAndView mav, RedirectAttributes attr) {
        service.deleteById(id);
        attr.addFlashAttribute("mensagem", "Competência removido com sucesso!");
        mav.setViewName("redirect:/competencias/list");
        return mav;
    }
}
