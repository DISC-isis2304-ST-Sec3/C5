package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.ReservaServicio;
import uniandes.edu.co.proyecto.repositorio.ReservaServicioRepository;

@Controller
public class ReservaServicioController {

    @Autowired
    private ReservaServicioRepository reservaServicioRepository;

    @GetMapping("/reservasServicios")
    public String reservasServicios(Model model){
        model.addAttribute("reservasServicios", reservaServicioRepository.darReservasServicios());
        return "reservasServicios";
    }

    @GetMapping("/reservasServicios/new")
    public String reservaServicioForm(Model model){
        model.addAttribute("reservaServicio", new ReservaServicio());
        return "reservaServicioNuevo";
    }

    @PostMapping("/reservasServicios/new/save")
    public String reservaServicioGuardar(@ModelAttribute ReservaServicio reservaServicio){
        reservaServicioRepository.insertarReservasServicio(reservaServicio.getFecha(), reservaServicio.getDuracion(), reservaServicio.getServicios_idServicio().getIdServicio(), reservaServicio.getReservas_idReserva().getIdReserva(), reservaServicio.getEmpleados_idEmpleado().getIdEmpleado());
        return "redirect:/reservasServicios";
    }

    @GetMapping("/reservasServicios/{idReservaServicio}/edit")
    public String reservaServicioEditarForm(@PathVariable("idReservaServicio") int idReservaServicio, Model model){
        ReservaServicio reservaServicio = reservaServicioRepository.darReservaServicio(idReservaServicio);
        if(reservaServicio != null){
            model.addAttribute("reservaServicio", reservaServicio);
            return "reservaServicioEditar";
        } else{
            return "redirect:/reservasServicios";
        }
    }

    @PostMapping("/reservasServicios/{idReservaServicio}/edit/save")
    public String reservaServicioEditarGuardar(@PathVariable("idReservaServicio") int idReservaServicio, @ModelAttribute ReservaServicio reservaServicio){
        reservaServicioRepository.actualizarReservasServicio(idReservaServicio, reservaServicio.getFecha(), reservaServicio.getDuracion(), reservaServicio.getServicios_idServicio().getIdServicio(), reservaServicio.getReservas_idReserva().getIdReserva(), reservaServicio.getEmpleados_idEmpleado().getIdEmpleado());
        return "redirect:/reservasServicios";
    }

    @GetMapping("/reservasServicios/{idReservaServicio}/delete")
    public String reservaServicioEliminar(@PathVariable("idReservaServicio") int idReservaServicio){
        reservaServicioRepository.eliminarReservaServicio(idReservaServicio);
        return "redirect:/reservasServicios";
    }
    
}
