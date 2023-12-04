package uniandes.edu.co.proyecto.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.modelo.Cliente;
import uniandes.edu.co.proyecto.modelo.HabitacionEmbedded;
import uniandes.edu.co.proyecto.repositorio.ClienteRepository;





@Controller
public class ClienteController {
    
    @Autowired
    private ClienteRepository clienteRepository;

    
    @GetMapping("/clientes")
    public String obtenerTodosLosClientes(Model model){
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes";
    }

    @GetMapping("/")
    public String home(Model model){
        return "index";
    }

    @GetMapping("/clientForm")
    public String mostrarFormulario(Model model) {
        // Creamos una instancia vacía para el nuevo BebidaTipos
        model.addAttribute("nuevoCliente", new Cliente());
        return "clientesForm";
    }

    @PostMapping("/crearCliente")
    public String crearBClientes(@ModelAttribute("nuevoCliente") Cliente nuevoCliente) {

        // Creamos una nueva bebida utilizando los datos del formulario
        HabitacionEmbedded nuevaHabitacion = new HabitacionEmbedded(
            nuevoCliente.getHabitacion().getNumero(),
            nuevoCliente.getHabitacion().getTipoHabitacion(),
            nuevoCliente.getHabitacion().isDisponible(),
            nuevoCliente.getHabitacion().getConsumoServicio()
        );

        // Agregamos la bebida a la lista de bebidas en el nuevo tipo de bebida
        nuevoCliente.setHabitacion(nuevaHabitacion);


        // Guardamos el nuevo tipo de bebida
        clienteRepository.save(nuevoCliente);
        return "redirect:/clientes";
    }

    @GetMapping("/addHabitacion")
    public String añadirHabitacion(@RequestParam(name = "_id", required = false) String id, Model model){
        model.addAttribute("_id", id);
        model.addAttribute("habitaciones", new HabitacionEmbedded());


        return "addHabitacionForm";
    }

    @PostMapping("/deleteCliente")
    public String eliminarCliente(@RequestParam(name = "_id", required = false) String id){
        
        clienteRepository.deleteById(id);

        return "redirect:/clientes";
        
    }

    @PostMapping("/addHablitacionSave")
    public String añadirHabitacionSave(@RequestParam("_id") String id,
    @ModelAttribute("hebitaciones") HabitacionEmbedded habitacion){

        // Creamos una nueva bebida utilizando los datos del formulario
        HabitacionEmbedded nuevaHabitacion = new HabitacionEmbedded(
            habitacion.getNumero(),
            habitacion.getTipoHabitacion(),
            habitacion.isDisponible(),
            habitacion.getConsumoServicio()
        );

        System.out.println(id);
        //Buscamos los tipos de bebida con ese nombre
        List<Cliente> clientes = clienteRepository.encontrarPorId(id);

        //Añadimos esa bebida a todos los tipos de bebidas con ese nombre
        for (Cliente cliente:clientes){
            if (cliente.getHabitacion() == null){
                cliente.setHabitacion(null);
            }
            cliente.setHabitacion(nuevaHabitacion);

            //Persistemos la modificacion en la base de datos
            clienteRepository.save(cliente);
        }
        
        return "redirect:/clientes";

    }

}
