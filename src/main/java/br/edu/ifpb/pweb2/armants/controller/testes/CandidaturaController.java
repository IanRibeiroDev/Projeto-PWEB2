package br.edu.ifpb.pweb2.armants.controller.testes;

import br.edu.ifpb.pweb2.armants.model.concreto.Candidatura;
import br.edu.ifpb.pweb2.armants.service.AlunoService;
import br.edu.ifpb.pweb2.armants.service.CandidaturaService;
import br.edu.ifpb.pweb2.armants.service.OfertaEstagioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/candidaturas")
public class CandidaturaController {
    @Autowired
    private CandidaturaService candidaturaService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private OfertaEstagioService ofertaEstagioService;

    @GetMapping
    public ModelAndView form(ModelAndView m) {
        m.addObject("candidatura", new Candidatura());
        m.addObject("candidatos", alunoService.findAll());
        m.addObject("ofertasEstagio", ofertaEstagioService.findAll());
        m.setViewName("testes/candidatura/form");
        return m;
    }

    @PostMapping
    public ModelAndView save(@Valid Candidatura candidatura, BindingResult result, ModelAndView m, RedirectAttributes attr) {
        if(candidaturaService.findByCandidatoAndOfertaEstagio(candidatura.getCandidato().getId(), candidatura.getOfertaEstagio().getId()) != null) {
            result.addError(new ObjectError("", ""));
            m.addObject("mensagem", "Aluno já se cadastrou nessa oferta!");
        }

        if(result.hasErrors()) {
            m.setViewName("testes/candidatura/form");
            m.addObject("candidatura", candidatura);
            m.addObject("candidatos", alunoService.findAll());
            m.addObject("ofertasEstagio", ofertaEstagioService.findAll());
            return m;
        }

        String operacao = (candidatura.getId() == null) ? "criada" : "salva";
        candidaturaService.save(candidatura);
        m.setViewName("redirect:/candidaturas/list");
        attr.addFlashAttribute("mensagem", "Candidatura " + operacao + " com sucesso!");
        return m;
    }

    @RequestMapping("/list")
    public ModelAndView list(ModelAndView m) {
        m.setViewName("testes/candidatura/list");
        m.addObject("candidaturas", candidaturaService.findAll());
        return m;
    }

    @RequestMapping("/{id}")
    public String getCandidaturaById(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("candidatura", candidaturaService.findById(id));
        model.addAttribute("candidatos", alunoService.findAll());
        model.addAttribute("ofertasEstagio", ofertaEstagioService.findAll());
        return "testes/candidatura/form";
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Integer id,
                                   ModelAndView mav, RedirectAttributes attr) {
        candidaturaService.deleteById(id);
        attr.addFlashAttribute("mensagem", "Candidatura removido com sucesso!");
        mav.setViewName("redirect:/candidaturas/list");
        return mav;
    }
}
