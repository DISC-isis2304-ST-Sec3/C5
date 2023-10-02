package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Plan;
import uniandes.edu.co.proyecto.repositorio.PlanRepository;



@Controller
public class PlanController {
    @Autowired
    private PlanRepository planRepository;

    @GetMapping("/planes")
    public String plan(Model model){
        model.addAttribute("planes", planRepository.darPlanes());
        return "planes";
    }
    @GetMapping("/planes/new")
    public String planForm(Model model){
        model.addAttribute("planes", new Plan());
        return "planNuevo";
    }
    @PostMapping("/planes/new/save")
    public String planGuardar(@ModelAttribute Plan plan){
        planRepository.insertarPlanes(plan.getDescuento(), plan.getPeriodoVigenciaInicial(), plan.getPeriodoVigenciaFinal());;
        return "redirect:/planes";
    }
    @GetMapping("/planes/{nombrePlan}/edit")
    public String planEditerForm(@PathVariable("nombrePlan") String nombrePlan, Model model){
        Plan plan = planRepository.darPlan(nombrePlan);
        if(plan != null){
            model.addAttribute("planes", plan);
            return "planEditar";
        } else {
            return "redirect:/planes";
        }
    }
    @PostMapping("/planes/{nombrePlan}/edit/save")
    public String planEditarGuardar(@PathVariable("nombrePlan") String nombrePlan, @ModelAttribute Plan plan){
        planRepository.actualizarPlanes(nombrePlan, plan.getDescuento(), plan.getPeriodoVigenciaInicial(), plan.getPeriodoVigenciaFinal());
        return "redirect:/planes";
    }
    @GetMapping("/planes/{nombrePlan}/edit/delete")
    public String planEliminar(@PathVariable("nombrePlan") String nombrePlan){
        planRepository.eliminarPlan(nombrePlan);;
        return "redirect:/planes";
    }
}
