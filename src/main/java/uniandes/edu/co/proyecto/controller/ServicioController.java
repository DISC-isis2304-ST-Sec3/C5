package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.ServicioEmbedded;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository;



@Controller
public class ServicioController {
    
    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping("/servicios")
    public String sevicios(Model model){
        model.addAttribute("servicios", servicioRepository.findAll());
        return "servicios";
    }

    @GetMapping("/serviciosForm")
    public String mostrarFormulario(Model model) {
        // Creamos una instancia vacía para el nuevo BebidaTipos
        model.addAttribute("nuevoServicio", new ServicioEmbedded());
        return "serviciosForm";
    }

    @GetMapping("/crearServicio")
    public String tipoForm(Model model){
        model.addAttribute("servicio", new ServicioEmbedded());
        return "servicioNuevo";
    }
    @PostMapping("/crearServicioNueva")
    public String crearTipoHNueva(@ModelAttribute("servicoNueva") ServicioEmbedded servicioEmbedded) {
        ServicioEmbedded nueva = new ServicioEmbedded(
            servicioEmbedded.getNombre(), servicioEmbedded.getDescripcion(), servicioEmbedded.getHorario(), servicioEmbedded.getCapacidad(), servicioEmbedded.getMenu()
        );
        servicioRepository.save(nueva);
        return "redirect:/servicios";
    }

    @PostMapping("/deleteservicio")
    public String eliminarServicio(@RequestParam(name = "_id", required = false) String id){
        
        servicioRepository.deleteById(id);

        return "redirect:/servicios";

    }

    @GetMapping("/servicios")
    public String obtenerTodasLosServicios(Model model) {
        model.addAttribute("servicios", servicioRepository.findAll());
        return "servicios";
    } 
}
