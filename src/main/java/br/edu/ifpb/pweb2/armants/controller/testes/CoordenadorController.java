package br.edu.ifpb.pweb2.armants.controller.testes;

import br.edu.ifpb.pweb2.armants.model.concreto.Coordenador;
import br.edu.ifpb.pweb2.armants.service.CoordenadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/coordenadores")
public class CoordenadorController {
    @Autowired
    private CoordenadorService service;

    @GetMapping
    public ModelAndView form(ModelAndView m) {
        m.addObject("coordenador", new Coordenador());
        m.setViewName("testes/coordenador/form");
        return m;
    }

    @PostMapping
    public ModelAndView save(@Valid Coordenador coordenador, BindingResult result, ModelAndView modelAndView, RedirectAttributes attr) {
        if(result.hasErrors()) {
            modelAndView.setViewName("testes/coordenador/form");
            modelAndView.addObject("coordenador", coordenador);
            return modelAndView;
        }

        String operacao = (coordenador.getId() == null) ? "criado" : "salvo";
        service.save(coordenador);
        modelAndView.setViewName("redirect:/coordenadores/list");
        attr.addFlashAttribute("mensagem", "Coordenador " + operacao + " com sucesso!");
        return modelAndView;
    }

    @RequestMapping("/list")
    public ModelAndView list(ModelAndView m) {
        m.setViewName("testes/coordenador/list");
        m.addObject("coordenadores", service.findAll());
        return m;
    }

    @RequestMapping("/{id}")
    public String getCoordenadorById(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("coordenador", service.findById(id));
        return "testes/coordenador/form";
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Integer id,
                                   ModelAndView mav, RedirectAttributes attr) {
        service.deleteById(id);
        attr.addFlashAttribute("mensagem", "Coordenador removido com sucesso!");
        mav.setViewName("redirect:/coordenadores/list");
        return mav;
    }
}
