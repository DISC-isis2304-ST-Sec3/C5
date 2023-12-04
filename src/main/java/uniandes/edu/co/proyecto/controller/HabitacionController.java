package uniandes.edu.co.proyecto.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.modelo.Cliente;
import uniandes.edu.co.proyecto.modelo.ConsumoServicio;
import uniandes.edu.co.proyecto.modelo.HabitacionEmbedded;
import uniandes.edu.co.proyecto.modelo.TipoHabitacionEmbedded;
import uniandes.edu.co.proyecto.repositorio.ConsumoServicioRepository;
import uniandes.edu.co.proyecto.repositorio.HabitacionRepository;
import uniandes.edu.co.proyecto.repositorio.ReservaAlojamientoRepository;



@Controller
public class HabitacionController {
    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private ConsumoServicioRepository consumoServicioRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/habitaciones")
    public String obtenerTodasLasHabitaciones(Model model){
        model.addAttribute("habitaciones", habitacionRepository.findAll());
        return "habitaciones";
    }

    @GetMapping("/")
    public String home(Model model){
        return "index";
    }

    @GetMapping("/mostrarResultadosAgregacion")
    public String mostrarResultados(Model model) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("consumosServicios")
                .localField("consumosServicios")
                .foreignField("_id")
                .as("consumosServicios_id");

        Aggregation aggregation = Aggregation.newAggregation(lookupOperation);

        List<HabitacionEmbedded> habitaciones = mongoTemplate.aggregate(aggregation, "habitaciones", HabitacionEmbedded.class).getMappedResults();
        model.addAttribute("habitaciones", habitaciones);

        return "resultados";
    }

    @GetMapping("/habitacionForm")
    public String mostrarFormulario(Model model) {
        // Creamos una instancia vacía para el nuevo BebidaTipos
        model.addAttribute("nuevaHabitacion", new HabitacionEmbedded());
        model.addAttribute("consumosServicios_id", consumoServicioRepo.findAll());
        return "HabitacionForm";
    }

    @PostMapping("/crearHabitacion")
    public String crearHabitacion(@ModelAttribute("nuevaHabitacion") HabitacionEmbedded nuevoHabitacionEmbedded) {

        // Creamos una nueva bebida utilizando los datos del formulario
        TipoHabitacionEmbedded nuevTipoHabitacionEmbedded = new TipoHabitacionEmbedded(
            nuevoHabitacionEmbedded.getTipoHabitacion().getNombreTipo(),
            nuevoHabitacionEmbedded.getTipoHabitacion().getDotacion(),
            nuevoHabitacionEmbedded.getTipoHabitacion().getCapacidad(),
            nuevoHabitacionEmbedded.getTipoHabitacion().getCostoPorNoche()
        );

        // Agregamos la bebida a la lista de bebidas en el nuevo tipo de bebida
        nuevoHabitacionEmbedded.setTipoHabitacion(nuevTipoHabitacionEmbedded);

        ConsumoServicio nuevoConsumo = new ConsumoServicio(
            nuevoHabitacionEmbedded.getConsumoServicio().get(0).getDescripcion(),
            nuevoHabitacionEmbedded.getConsumoServicio().get(0).getFecha(),
            nuevoHabitacionEmbedded.getConsumoServicio().get(0).getCosto()
        );

        consumoServicioRepo.save(nuevoConsumo);
        nuevoHabitacionEmbedded.setConsumoServicio(Collections.singletonList(nuevoConsumo));

        // Guardamos el nuevo tipo de bebida
        habitacionRepository.save(nuevoHabitacionEmbedded);
        return "redirect:/habitaciones";
    }

    @GetMapping("/addHabitacion")
    public String añadirBebida(@RequestParam(name = "_id", required = false) String id, Model model){
        model.addAttribute("_id", id);
        model.addAttribute("tipoHabitacion", new TipoHabitacionEmbedded());


        return "addHabitacionForm";
    }

    @PostMapping("/deleteHabitacion")
    public String eliminarBebidaTipo(@RequestParam(name = "_id", required = false) String id){
        
        habitacionRepository.deleteById(id);

        return "redirect:/habitaciones";
        
    }

    @PostMapping("/addTipoSave")
    public String añadirTipoSave(@RequestParam("_id") String id,
    @ModelAttribute("tipoHabitacion") TipoHabitacionEmbedded tipo){

        // Creamos una nueva bebida utilizando los datos del formulario
        TipoHabitacionEmbedded nuevoTipo = new TipoHabitacionEmbedded(
            tipo.getNombreTipo(),
            tipo.getDotacion(),
            tipo.getCapacidad(),
            tipo.getCostoPorNoche()
        );

        System.out.println(id);
        //Buscamos los tipos de bebida con ese nombre
        List<HabitacionEmbedded> habitacionesEmbeddeds = habitacionRepository.encontrarPorId(id);

        //Añadimos esa bebida a todos los tipos de bebidas con ese nombre
        for (HabitacionEmbedded habitacion:habitacionesEmbeddeds){
            if (habitacion.getTipoHabitacion() == null){
                habitacion.setTipoHabitacion(null);;
            }
            habitacion.setTipoHabitacion(tipo);

            //Persistemos la modificacion en la base de datos
            habitacionRepository.save(habitacion);
        }
        
        return "redirect:/habitaciones";

    }
}
