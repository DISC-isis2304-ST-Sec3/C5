package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Plan;
import uniandes.edu.co.proyecto.modelo.PlanServicio;
import uniandes.edu.co.proyecto.modelo.PlanServicioPK;
import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.repositorio.PlanRepository;
import uniandes.edu.co.proyecto.repositorio.PlanServicioRepository;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository;

@Controller
public class PlanServicioController {

    @Autowired
    private PlanServicioRepository planServicioRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private PlanRepository planRepository;

    @GetMapping("/planesServicios/new")
    public String planesForm(Model model){
        model.addAttribute("servicios", servicioRepository.darServicios());
        model.addAttribute("planes", planRepository.darPlanes());
        return "planServicioNuevo";
    }

    @PostMapping("/planesServicios/new/save")
    public String planGuardar(@ModelAttribute("Servicio_idServicio") int Servicio_idServicio, @ModelAttribute("Planes_nombrePlan") String Planes_nombrePlan ){
        Servicio servicio = servicioRepository.darServicio(Servicio_idServicio);
        Plan plan = planRepository.darPlan(Planes_nombrePlan);
        PlanServicioPK pk = new PlanServicioPK(plan, servicio);
        PlanServicio planServicio = new PlanServicio();
        planServicio.setPk(pk);
        planServicioRepository.insertarPlanServicio(planServicio.getPk().getServicios_idServicios().getIdServicio(), planServicio.getPk().getPlanes_nombrePlan().getNombrePlan());
        return "redirect:/planes";
    }


    
}
