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

import uniandes.edu.co.proyecto.modelo.TipoHabitacionEmbedded;
import uniandes.edu.co.proyecto.repositorio.TipoHabitacionRepository;

@Controller
public class TipoHabitacionController {

    @Autowired
    private TipoHabitacionRepository tipoHRepository;

    @GetMapping("/tiposHabitaciones")
    public String tiposHabitaciones(Model model){
        model.addAttribute("tiposHabitaciones", tipoHRepository.findAll());
        return "tiposHabitaciones";
    }

    @GetMapping("/tiposHForm")
    public String mostrarFormulario(Model model) {
        // Creamos una instancia vacía para el nuevo BebidaTipos
        model.addAttribute("nuevoTipo", new TipoHabitacionEmbedded());
        return "tiposHabitacionesForm";
    }

    @GetMapping("/crearTipo")
    public String tipoForm(Model model){
        model.addAttribute("tipoHabitacion", new TipoHabitacionEmbedded());
        return "tipoHNuevo";
    }
    @PostMapping("/crearTipoHNueva")
    public String crearTipoHNueva(@ModelAttribute("tipoHNueva") TipoHabitacionEmbedded tipoHabitacionEmbedded) {
        TipoHabitacionEmbedded nueva = new TipoHabitacionEmbedded(
            tipoHabitacionEmbedded.getNombreTipo(), tipoHabitacionEmbedded.getDotacion(), tipoHabitacionEmbedded.getCapacidad(), tipoHabitacionEmbedded.getCostoPorNoche()
        );
        tipoHRepository.save(nueva);
        return "redirect:/tiposHabitaciones";
    }

    @PostMapping("/deletetipoHabitacion")
    public String eliminarTipoHabitacion(@RequestParam(name = "_id", required = false) String id){
        
        tipoHRepository.deleteById(id);

        return "redirect:/tiposHabitaciones";

    }

    @GetMapping("/tiposHabitaciones")
    public String obtenerTodasLosTiposHabitaciones(Model model) {
        model.addAttribute("tiposHabitaciones", tipoHRepository.findAll());
        return "tiposHabitaciones";
    } 
    
}
