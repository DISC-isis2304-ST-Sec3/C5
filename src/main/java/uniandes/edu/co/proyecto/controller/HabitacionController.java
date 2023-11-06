package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Habitacion;
import uniandes.edu.co.proyecto.repositorio.HabitacionRepository;


@Controller
public class HabitacionController {
    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping("/habitaciones")
    public String habitaciones(Model model){
        model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
        return "habitaciones";
    }
    @GetMapping("/habitaciones/new")
    public String habitacionForm(Model model){
        model.addAttribute("habitacion", new Habitacion());
        return "habitacionNuevo";
    }
    @PostMapping("/habitaciones/new/save")
    public String habitacionGuardar(@ModelAttribute Habitacion habitacion){
        habitacionRepository.insertarHabitaciones(habitacion.getTipoH_nombreTipo().getNombreTipo());
        return "redirect:/habitaciones";
    }
    @GetMapping("/habitaciones/{numero}/edit")
    public String habitacionEditerForm(@PathVariable("numero") int numero, Model model){
        Habitacion habitacion = habitacionRepository.darHabitacion((numero));
        if(habitacion != null){
            model.addAttribute("numero", numero);
            return "habitacionEditar";
        } else {
            return "redirect:/habitaciones";
        }
    }
    @PostMapping("/habitaciones/{numero}/edit/save")
    public String habitacionEditarGuardar(@PathVariable("numero") int numero, @ModelAttribute Habitacion habitacion){
        habitacionRepository.actualizarHabitaciones(numero, habitacion.getTipoH_nombreTipo().getNombreTipo());;
        return "redirect:/habitaciones";
    }
    @GetMapping("/habitaciones/{numero}/edit/delete")
    public String habitacionEliminar(@PathVariable("numero") int numero){
        habitacionRepository.eliminarHabitacion(numero);;
        return "redirect:/habitaciones";
    }
}
