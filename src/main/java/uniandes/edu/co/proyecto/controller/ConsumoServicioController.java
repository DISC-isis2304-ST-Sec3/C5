package uniandes.edu.co.proyecto.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.modelo.ConsumoServicio;
import uniandes.edu.co.proyecto.modelo.ServicioEmbedded;
import uniandes.edu.co.proyecto.repositorio.ConsumoServicioRepository;

@Controller
public class ConsumoServicioController {
    
    @Autowired
    private ConsumoServicioRepository consumoRepository;

  
    @GetMapping("/consumoServicios")
    public String obtenerTodasLasBebidasTipos(Model model){
        model.addAttribute("consumoServicios", consumoRepository.findAll());
        return "consumoServicios";
    }

    @GetMapping("/")
    public String home(Model model){
        return "index";
    }

    @GetMapping("/consumoForm")
    public String mostrarFormulario(Model model) {
        // Creamos una instancia vacía para el nuevo BebidaTipos
        model.addAttribute("nuevoConsumo", new ConsumoServicio());
        return "consumoServicioForm";
    }

    @PostMapping("/crearConsumoServicio")
    public String crearConsumo(@ModelAttribute("nuevoConsumo") ConsumoServicio nuevoConsumo) {

        // Creamos una nueva bebida utilizando los datos del formulario
        ServicioEmbedded nuevaServicio = new ServicioEmbedded(
            nuevoConsumo.getServicios().getNombre(),
            nuevoConsumo.getServicios().getDescripcion(),
            nuevoConsumo.getServicios().getHorario(),
            nuevoConsumo.getServicios().getCapacidad(),
            nuevoConsumo.getServicios().getMenu()
        );

        // Agregamos la bebida a la lista de bebidas en el nuevo tipo de bebida
        nuevoConsumo.setServicios(nuevaServicio);


        // Guardamos el nuevo tipo de bebida
        consumoRepository.save(nuevoConsumo);
        return "redirect:/consumoServicios";
    }

    @GetMapping("/addServicio")
    public String añadirServicio(@RequestParam(name = "_id", required = false) String id, Model model){
        model.addAttribute("_id", id);
        model.addAttribute("servicio", new ServicioEmbedded());


        return "addServicioForm";
    }

    @PostMapping("/deleteConsumo")
    public String eliminarConsumo(@RequestParam(name = "_id", required = false) String id){
        
        consumoRepository.deleteById(id);

        return "redirect:/consumoServicios";
        
    }

    @PostMapping("/addServicioSave")
    public String añadirServicioSave(@RequestParam("_id") String id,
    @ModelAttribute("servicio") ServicioEmbedded servicio){

        // Creamos una nueva bebida utilizando los datos del formulario
        ServicioEmbedded nuevoSercicio = new ServicioEmbedded(
            servicio.getNombre(),
            servicio.getDescripcion(),
            servicio.getHorario(),
            servicio.getCapacidad(),
            servicio.getMenu()
        );

        System.out.println(id);
        //Buscamos los tipos de bebida con ese nombre
        List<ConsumoServicio> consumos = consumoRepository.encontrarPorId(id);

        //Añadimos esa bebida a todos los tipos de bebidas con ese nombre
        for (ConsumoServicio consumo:consumos){
            if (consumo.getServicios() == null){
                consumo.setServicios(null);;
            }
            consumo.setServicios(servicio);;

            //Persistemos la modificacion en la base de datos
            consumoRepository.save(consumo);
        }
        
        return "redirect:/consumoServicios";

    }
}
