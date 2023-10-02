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
    private PlanRepository palnRepository;

    @GetMapping("/planes")
    public String plan(Model model){
        model.addAttribute("planes", palnRepository.darPlanes());
        return "planes";
    }
    @GetMapping("/planes/new")
    public String planForm(Model model){
        model.addAttribute("planes", new Plan());
        return "planNuevo";
    }
    @PostMapping("/planes/new/save")
    public String planGuardar(@ModelAttribute Plan plan){
        palnRepository.insertarPlanes(plan.getDescuento(), plan.getPeriodoVigenciaInicial(), plan.getPeriodoVigenciaFinal());;
        return "redirect:/planes";
    }
    @GetMapping("/planes/{nombrePlan}/edit")
    public String planEditerForm(@PathVariable("nombrePlan") String nombrePlan, Model model){
        Plan plan = palnRepository.darPlan(nombrePlan);
        if(plan != null){
            model.addAttribute("planes", plan);
            return "planEditar";
        } else {
            return "redirect:/planes";
        }
    }
    @PostMapping("/planes/{nombrePlan}/edit/save")
    public String planEditarGuardar(@PathVariable("nombrePlan") String nombrePlan, @ModelAttribute Plan plan){
        palnRepository.actualizarPlanes(nombrePlan, plan.getDescuento(), plan.getPeriodoVigenciaInicial(), plan.getPeriodoVigenciaFinal());
        return "redirect:/planes";
    }
    @GetMapping("/planes/{nombrePlan}/edit/delete")
    public String planEliminar(@PathVariable("nombrePlan") String nombrePlan){
        palnRepository.eliminarPlan(nombrePlan);;
        return "redirect:/planes";
    }
}
