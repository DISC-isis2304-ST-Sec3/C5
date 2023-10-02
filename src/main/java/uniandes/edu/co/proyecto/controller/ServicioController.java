package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository;



@Controller
public class ServicioController {
    
    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping("/servicios")
    public String servicio(Model model){
        model.addAttribute("servicios", servicioRepository.darServicios());
        return "servicios";
    }
    @GetMapping("/servicios/new")
    public String servicioForm(Model model){
        model.addAttribute("servicio", new Servicio());
        return "servicioNuevo";
    }
    @PostMapping("/servicios/new/save")
    public String servicioGuardar(@ModelAttribute Servicio servicio){
        servicioRepository.insertarServicios(servicio.getNombreServicio(), servicio.getDescripcion(), servicio.getHorario(), servicio.getCapacidad(), servicio.getCosto(), servicio.getMenu());
        return "redirect:/servicios";
    }
    @GetMapping("/servicios/{idServicio}/edit")
    public String servicioEditerForm(@PathVariable("idServicio") int idServicio, Model model){
        Servicio servicio = servicioRepository.darServicio(idServicio);
        if(servicio != null){
            model.addAttribute("servicio", servicio);
            return "servicioEditar";
        } else {
            return "redirect:/servicios";
        }
    }
    @PostMapping("/servicios/{idServicio}/edit/save")
    public String servicioEditarGuardar(@PathVariable("idServicio") int idServicio, @ModelAttribute Servicio servicio){
        servicioRepository.actualizarServicios(idServicio, servicio.getNombreServicio(), servicio.getDescripcion(), servicio.getHorario(), servicio.getCapacidad(), servicio.getCosto(), servicio.getMenu());
        return "redirect:/servicios";
    }
    @GetMapping("/servicios/{idServicio}/edit/delete")
    public String servicioEliminar(@PathVariable("idServicio") int idServicio){
        servicioRepository.eliminarServicio(idServicio);
        return "redirect:/servicios";
    }
}
