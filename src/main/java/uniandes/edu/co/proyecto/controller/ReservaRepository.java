package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import uniandes.edu.co.proyecto.modelo.Reserva;
import uniandes.edu.co.proyecto.repositorio.ReservasRepository;

@Controller
public class ReservaRepository {

    @Autowired
    private ReservasRepository reservasRepository;

    @GetMapping("/reservas")
    public String reservas(Model model){
        model.addAttribute("reservas", reservasRepository.darReservas());
        return "reservas";
    }
    @GetMapping("/reservas/new")
    public String reservaForm(Model model){
        model.addAttribute("tipoH", new Reserva());
        return "reservaNuevo";
    }
    @PostMapping("/reservas/new/save")
    public String reservaGuardar(@ModelAttribute Reserva reserva){
        reservasRepository.insertarReservas(reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getCantidadPersonas(), reserva.getUsuario_cedula().getCedula(), reserva.getPlanes_nombrePlan().getNombrePlan(), reserva.getHabitaciones_numero().getNumero());;
        return "redirect:/reservas";
    }
    @GetMapping("/reservas/{idReserva}/edit")
    public String reservaEditerForm(@PathVariable("idReserva") int idReserva, Model model){
        Reserva reserva = reservasRepository.darReserva(idReserva);
        if(reserva != null){
            model.addAttribute("reserva", reserva);
            return "reservaEditar";
        } else {
            return "redirect:/reservas";
        }
    }
    @PostMapping("/reservas/{idReserva}/edit/save")
    public String reservaEditarGuardar(@PathVariable("idReserva") int idReserva, @ModelAttribute Reserva reserva){
        reservasRepository.actualizarReservas(idReserva, reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getCantidadPersonas(), reserva.getUsuario_cedula().getCedula(), reserva.getPlanes_nombrePlan().getNombrePlan(), reserva.getHabitaciones_numero().getNumero());;
        return "redirect:/habitreservasaciones";
    }
    @GetMapping("/reservas/{idReserva}/edit/delete")
    public String reservaEliminar(@PathVariable("idReserva") int idReserva){
        reservasRepository.eliminarReserva(idReserva);
        return "redirect:/reservas";
    }
    
}
