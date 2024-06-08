package br.edu.ifpb.pweb2.armants.controller.testes;

import br.edu.ifpb.pweb2.armants.model.concreto.Candidatura;
import br.edu.ifpb.pweb2.armants.model.concreto.OfertaEstagio;
import br.edu.ifpb.pweb2.armants.service.CandidaturaService;
import br.edu.ifpb.pweb2.armants.service.CompetenciaService;
import br.edu.ifpb.pweb2.armants.service.EmpresaService;
import br.edu.ifpb.pweb2.armants.service.OfertaEstagioService;
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

import java.sql.DriverManager;
import java.util.List;

@Controller
@RequestMapping("/ofertas")
public class OfertaEstagioController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private OfertaEstagioService ofertaEstagioService;

    @Autowired
    private CompetenciaService competenciaService;

    @Autowired
    private CandidaturaService candidaturaService;

    @GetMapping
    public ModelAndView form(ModelAndView m) {
        m.addObject("oferta", new OfertaEstagio());
        m.addObject("empresas", empresaService.findAll());
        m.addObject("competencias", competenciaService.findAll());
        m.setViewName("testes/oferta_estagio/form");
        return m;
    }

    @PostMapping
    public ModelAndView save(@Valid OfertaEstagio ofertaEstagio, BindingResult result, ModelAndView m, RedirectAttributes attr) {
        if(result.hasErrors()) {
            m.setViewName("testes/oferta_estagio/form");
            m.addObject("oferta", ofertaEstagio);
            m.addObject("empresas", empresaService.findAll());
            m.addObject("competencias", competenciaService.findAll());
            return m;
        }

        String operacao = (ofertaEstagio.getId() == null) ? "criada" : "salva";
        ofertaEstagioService.save(ofertaEstagio);
        m.setViewName("redirect:/ofertas/list");
        attr.addFlashAttribute("mensagem", "Oferta " + operacao + " com sucesso!");
        return m;
    }

    @RequestMapping("/list")
    public ModelAndView list(ModelAndView m) {
        m.setViewName("testes/oferta_estagio/list");
        m.addObject("ofertas", ofertaEstagioService.findAll());
        return m;
    }

    @RequestMapping("/{id}")
    public String getOfertaById(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("oferta", ofertaEstagioService.findById(id));
        model.addAttribute("empresas", empresaService.findAll());
        model.addAttribute("competencias", competenciaService.findAll());
        return "testes/oferta_estagio/form";
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Integer id,
                                   ModelAndView mav, RedirectAttributes attr) {

        List<Candidatura> candidaturas = candidaturaService.findByOfertaEstagio_Id(id);

        for(Candidatura candidatura : candidaturas) {
            candidatura.setOfertaEstagio(null);
            candidatura.setStatus(Candidatura.Status.NEGADA);
            candidaturaService.save(candidatura);
        }

        ofertaEstagioService.deleteById(id);
        attr.addFlashAttribute("mensagem", "Oferta removida com sucesso!");
        mav.setViewName("redirect:/ofertas/list");
        return mav;
    }
}
