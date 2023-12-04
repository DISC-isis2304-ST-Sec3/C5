package uniandes.edu.co.proyecto.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.repositorio.HabitacionRepository.RespuestaRFC1;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository.RespuestaRFC8;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository.RespuestaRFC2;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository.RespuestaRFC4;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository;



@Controller
public class ServicioController {
    
    
    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping("/servicios")
    public String servicio(Model model, String RF2FechaInicio, String RF2FechaFin, String RF4CostoInicial, String RF4CostoFin, String RF4FechaInicio, String RF4FechaFin, String nameServicio){

        //Collection<RespuestaRFC8> RF8 = servicioRepository.darServiciosPocaDemanda();
        //model.addAttribute("idServicio", RF8.iterator().next().getIDServicio());
        //model.addAttribute("nombreServicio", RF8.iterator().next().getNombreServicio());
        //model.addAttribute("solicitudes_semanales", RF8.iterator().next().getSolicitudesSemanales());
        

        if((RF2FechaInicio == null || RF2FechaInicio.equals("")) || (RF2FechaFin == null || RF2FechaFin.equals("")) || (RF4CostoInicial == null || RF4CostoInicial.equals("")) || (RF4CostoFin == null || RF4CostoFin.equals("")) || (RF4FechaInicio == null || RF4FechaInicio.equals("")) || (RF4FechaFin == null || RF4FechaFin.equals("")))
        {
            model.addAttribute("servicios", servicioRepository.darServicios());
        }
        else if ((RF2FechaInicio == null || RF2FechaInicio.equals("")) || (RF2FechaFin == null || RF2FechaFin.equals("")))
        {
            Collection<RespuestaRFC4> RF4 = servicioRepository.darServiciosQueCumplenConCaracteristica(Integer.parseInt(RF4CostoInicial),Integer.parseInt(RF4CostoFin),RF4FechaInicio,RF4FechaFin, nameServicio);
            model.addAttribute("nombreServicio", RF4.iterator().next().getNombreServicio());
            model.addAttribute("fecha", RF4.iterator().next().getfecha());
            model.addAttribute("duracion", RF4.iterator().next().getDuracion());
            model.addAttribute("costo", RF4.iterator().next().getCosto());
        }else
        {
            Collection<RespuestaRFC2> RF2 = servicioRepository.dar20MasPopulares(RF2FechaInicio, RF2FechaFin);
            model.addAttribute("nombreServicio", RF2.iterator().next().gettotal_consumos());
            model.addAttribute("fecha", RF2.iterator().next().getServicio());
        }
         

        
        return "servicios";
    }
    @GetMapping("/servicios/new")
    public String servicioForm(Model model){
        model.addAttribute("servicio", new Servicio());
        return "servicioNuevo";
    }
    @PostMapping("/servicios/new/save")
    public String servicioGuardar(@ModelAttribute Servicio servicio){
        servicioRepository.insertarServicios(servicio.getNombreServicio(), servicio.getDescripcion(), servicio.getHorario(), servicio.getCapacidad(), servicio.getCosto());
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
        servicioRepository.actualizarServicios(idServicio, servicio.getNombreServicio(), servicio.getDescripcion(), servicio.getHorario(), servicio.getCapacidad(), servicio.getCosto());
        return "redirect:/servicios";
    }
    @GetMapping("/servicios/{idServicio}/edit/delete")
    public String servicioEliminar(@PathVariable("idServicio") int idServicio){
        servicioRepository.eliminarServicio(idServicio);
        return "redirect:/servicios";
    }




}
