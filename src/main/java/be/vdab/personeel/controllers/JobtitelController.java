package be.vdab.personeel.controllers;

import be.vdab.personeel.services.JobtitelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jobtitel")
public class JobtitelController {
    private final JobtitelService jobtitelService;

    public JobtitelController(JobtitelService jobtitelService) {
        this.jobtitelService = jobtitelService;
    }


    @GetMapping
    public ModelAndView jobtitels() {
        ModelAndView modelAndView = new ModelAndView("jobtitel", "jobtitels", jobtitelService.findAll());
        return modelAndView;
    }

    @GetMapping("{id}")
    public ModelAndView jobtitelSpecifiek(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("jobtitel", "jobtitels", jobtitelService.findAll());
        jobtitelService.findById(id).ifPresent(jobtitel ->
                modelAndView.addObject(jobtitel));
        return modelAndView;
    }
}
