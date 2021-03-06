package be.vdab.personeel.controllers;

import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.forms.OpslagForm;
import be.vdab.personeel.forms.RijksregisterForm;
import be.vdab.personeel.services.WerknemerService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/werknemer")
public class WerknemerController {
    private final WerknemerService werknemerService;


    public WerknemerController(WerknemerService werknemerService) {
        this.werknemerService = werknemerService;
    }

    @GetMapping("{id}")
    public ModelAndView werknemers(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("werknemer");
        werknemerService.findById(id).ifPresent(modelAndView::addObject);
        return modelAndView;
    }

    @GetMapping("{id}/opslag")
    public ModelAndView opslagPaginaForm(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("opslag");
        werknemerService.findById(id).ifPresent(werknemer -> {
            modelAndView.addObject(werknemer).addObject(new OpslagForm(null));

        });
        return modelAndView;
    }

    @PostMapping("{id}/opslag/toevoegen")
    public ModelAndView opslagtoevoegen(@PathVariable long id, @Valid OpslagForm form, Errors errors, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", id);
        ModelAndView modelAndView = new ModelAndView("opslag");
        werknemerService.findById(id).ifPresent(modelAndView::addObject);
        if (!errors.hasErrors()) {
            werknemerService.opslagBedrag(id, form.getBedrag());
            return new ModelAndView("redirect:/werknemer/{id}");
        }
        return modelAndView;
    }

    @GetMapping("{id}/rijksregisternummer")
    public ModelAndView rijksregisterPagina(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("rijksregisternummer");
        werknemerService.findById(id).ifPresent(werknemer -> {
            modelAndView.addObject(werknemer).addObject(new RijksregisterForm(werknemer.getId(), String.valueOf(werknemer.getRijksregisternr())));
        });
        return modelAndView;
    }

    @PostMapping("{id}/rijksregisternummer/toevoegen")
    public ModelAndView rijksregistertoevoegen(@PathVariable long id, @Valid RijksregisterForm rijksregisterForm, Errors errors, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", id);
        ModelAndView modelAndView = new ModelAndView("rijksregisternummer");
        Optional<Werknemer> werknemer = werknemerService.findById(id);
        werknemer.ifPresent(modelAndView::addObject);

        if (errors.hasErrors()) {
           return modelAndView;
        }
        werknemerService.setRijksregister(id, rijksregisterForm.getRijksregisternummer());
        return new ModelAndView("redirect:/werknemer/{id}");

    }
}
