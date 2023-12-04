package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.modelo.ReservaAlojamiento;
import uniandes.edu.co.proyecto.repositorio.ReservaAlojamientoRepository;

@Controller
public class ReservaAlojamientoController {

    @Autowired
    private ReservaAlojamientoRepository reservasRepository;

    @GetMapping("/reservas")
    public String reservas(Model model){
        model.addAttribute("reservas", reservasRepository.findAll());
        return "reservas";
    }

    @GetMapping("/reservasForm")
    public String mostrarFormulario(Model model) {
        // Creamos una instancia vacía para el nuevo BebidaTipos
        model.addAttribute("nuevaReserva", new ReservaAlojamiento());
        return "reservaAlojamientoForm";
    }

    @GetMapping("/crearReserva")
    public String reservaForm(Model model){
        model.addAttribute("reservaAlojamiento", new ReservaAlojamiento());
        return "reservaNuevo";
    }
    @PostMapping("/crearReservaNueva")
    public String crearReservaNueva(@ModelAttribute("reservaNueva") ReservaAlojamiento reservaAloj) {
        ReservaAlojamiento nueva = new ReservaAlojamiento(
            reservaAloj.getFechaEntrada(), reservaAloj.getFechaSalida(), reservaAloj.getCantidadPersonas(), reservaAloj.getNumeroNoches()
        );
        reservasRepository.save(nueva);
        return "redirect:/reservas";
    }

    @PostMapping("/deleteReserva")
    public String eliminarReserva(@RequestParam(name = "_id", required = false) String id){
        
        reservasRepository.deleteById(id);

        return "redirect:/reservas";

    }

    @GetMapping("/reservas")
    public String obtenerTodasLasReservas(Model model) {
        model.addAttribute("reservas", reservasRepository.findAll());
        return "reservas";
    } 
}
