package br.edu.ifpb.pweb2.armants.controller.testes;

import br.edu.ifpb.pweb2.armants.model.concreto.Coordenador;
import br.edu.ifpb.pweb2.armants.model.concreto.Empresa;
import br.edu.ifpb.pweb2.armants.service.CoordenadorService;
import br.edu.ifpb.pweb2.armants.service.EmpresaService;
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
@RequestMapping("/empresas")
public class EmpresaController {
    @Autowired
    private EmpresaService service;

    @GetMapping
    public ModelAndView form(ModelAndView m) {
        m.addObject("empresa", new Empresa());
        m.setViewName("testes/empresa/form");
        return m;
    }

    @PostMapping
    public ModelAndView save(@Valid Empresa empresa, BindingResult result, ModelAndView modelAndView, RedirectAttributes attr) {
        if(result.hasErrors()) {
            modelAndView.setViewName("testes/empresa/form");
            modelAndView.addObject("empresa", empresa);
            return modelAndView;
        }

        String operacao = (empresa.getId() == null) ? "criada" : "salva";
        service.save(empresa);
        modelAndView.setViewName("redirect:/empresas/list");
        attr.addFlashAttribute("mensagem", "Empresa " + operacao + " com sucesso!");
        return modelAndView;
    }

    @RequestMapping("/list")
    public ModelAndView list(ModelAndView m) {
        m.setViewName("testes/empresa/list");
        m.addObject("empresas", service.findAll());
        return m;
    }

    @RequestMapping("/{id}")
    public String getCoordenadorById(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("empresa", service.findById(id));
        return "testes/empresa/form";
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Integer id,
                                   ModelAndView mav, RedirectAttributes attr) {
        service.deleteById(id);
        attr.addFlashAttribute("mensagem", "Empresa removida com sucesso!");
        mav.setViewName("redirect:/empresas/list");
        return mav;
    }
}
