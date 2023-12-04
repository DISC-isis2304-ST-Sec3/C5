package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import uniandes.edu.co.proyecto.modelo.Reserva;
import uniandes.edu.co.proyecto.repositorio.ReservaRepository;
import uniandes.edu.co.proyecto.repositorio.ReservaRepository.RespuestaRFC6;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository.RespuestaRFC8;

@Controller
public class ReservaController {

    @Autowired
    private ReservaRepository reservasRepository;

    @GetMapping("/reservas")
    public String reservas(Model model){

        //Collection<RespuestaRFC6> RF6 = reservasRepository.analizarOperacion();
        //model.addAttribute("fecha_maxima_ocupacion", RF6.iterator().next().getfecha_maxima_ocupacion());
        //model.addAttribute("fecha_maximos_ingresos", RF6.iterator().next().getfecha_maximos_ingresos());
        //model.addAttribute("fecha_minima_demanda", RF6.iterator().next().getfecha_minima_demanda());
        

        model.addAttribute("reservas", reservasRepository.darReservas());
        return "reservas";
    }
    @GetMapping("/reservas/new")
    public String reservaForm(Model model){
        model.addAttribute("reserva", new Reserva());
        return "reservaNuevo";
    }
    @PostMapping("/reservas/new/save")
    public String reservaGuardar(@ModelAttribute Reserva reserva){
        reservasRepository.insertarReservas(reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getCantidadPersonas(), reserva.getUsuario_idUser().getidUser(), reserva.getPlanes_nombrePlan().getNombrePlan(), reserva.getHabitaciones_numero().getNumero());
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
        reservasRepository.actualizarReservas(idReserva, reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getCantidadPersonas(), reserva.getUsuario_idUser().getidUser(), reserva.getPlanes_nombrePlan().getNombrePlan(), reserva.getHabitaciones_numero().getNumero());
        return "redirect:/reservas";
    }
    @GetMapping("/reservas/{idReserva}/edit/delete")
    public String reservaEliminar(@PathVariable("idReserva") int idReserva){
        reservasRepository.eliminarReserva(idReserva);
        return "redirect:/reservas";
    }
    
}
