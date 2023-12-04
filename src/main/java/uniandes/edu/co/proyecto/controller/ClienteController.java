package uniandes.edu.co.proyecto.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import javax.swing.text.html.Option;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.FindAndModifyOptions;

import uniandes.edu.co.proyecto.modelo.Cliente;
import uniandes.edu.co.proyecto.modelo.ConsumoServicio;
import uniandes.edu.co.proyecto.modelo.HabitacionEmbedded;
import uniandes.edu.co.proyecto.modelo.ReservaAlojamiento;
import uniandes.edu.co.proyecto.repositorio.ClienteRepository;
import uniandes.edu.co.proyecto.repositorio.ConsumoServicioRepository;
import uniandes.edu.co.proyecto.repositorio.ReservaAlojamientoRepository;





@Controller
public class ClienteController {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ReservaAlojamientoRepository reservaAlojamientoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    
    @GetMapping("/clientes")
    public String obtenerTodosLosClientes(Model model){
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes";
    }

    @GetMapping("/")
    public String home(Model model){
        return "index";
    }

    //Referenciado
    @GetMapping("/mostrarResultadosAgregacion")
    public String mostrarResultados(Model model) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("reservasAlojamientos")
                .localField("reservasAlojamientos")
                .foreignField("_id")
                .as("reservaAlojamiento_id");

        Aggregation aggregation = Aggregation.newAggregation(lookupOperation);

        List<Cliente> clientes = mongoTemplate.aggregate(aggregation, "clientes", Cliente.class).getMappedResults();
        model.addAttribute("clientes", clientes);

        return "resultados";
    }

    @GetMapping("/clientForm")
    public String mostrarFormulario(Model model) {
        // Creamos una instancia vacía para el nuevo BebidaTipos
        model.addAttribute("nuevoCliente", new Cliente());
        model.addAttribute("reservaAlojamiento_id", reservaAlojamientoRepository.findAll());
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


        ReservaAlojamiento nuevaReservaAlojamiento = new ReservaAlojamiento(
            nuevoCliente.getReservaAlojamiento().get(0).getFechaEntrada(),
            nuevoCliente.getReservaAlojamiento().get(0).getFechaSalida(),
            nuevoCliente.getReservaAlojamiento().get(0).getCantidadPersonas(),
            nuevoCliente.getReservaAlojamiento().get(0).getNumeroNoches()
        );

        reservaAlojamientoRepository.save(nuevaReservaAlojamiento);
            
        nuevoCliente.setReservaAlojamiento(Collections.singletonList(nuevaReservaAlojamiento));

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
    @ModelAttribute("habitacion") HabitacionEmbedded habitacion){

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

    public void actualizarReservaTerminada(String clienteId) {
    // Crea un criterio para buscar el cliente por su ID
    Criteria criteria = Criteria.where("_id").is(clienteId);

    // Crea la consulta con el criterio
    Query query = new Query(criteria);

    // Crea la actualización para establecer la reservaTerminada a true
    Update update = new Update().set("reservaTerminada", true);

    // Realiza la actualización utilizando el método updateFirst
    mongoTemplate.updateFirst(query, update, Cliente.class);

}


@GetMapping("/mostrarResultadosAgregacion")
    public String mostrarResultadosRFC2(Model model) {
        // Definir las operaciones de la agregación
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("ReservasAlojamientos")
                .localField("reservaAlojamiento_id")
                .foreignField("_id")
                .as("reservaAlojamientoInfo");

        ProjectionOperation projectionOperation = Aggregation.project()
                .and("_id").as("_id")
                .and("habitacion.numero").as("numeroHab")
                .and(ArithmeticOperators.Divide.valueOf(
                        ArrayOperators.ArrayElemAt.arrayOf("reservaAlojamientoInfo.numeroNoches").elementAt(0))
                        .divideBy(365)).as("porcentajeOcupacion");

        MatchOperation matchOperation = Aggregation.match(
                Criteria.where("reservaAlojamientoInfo.fechaEntrada").gte("2023-01-01T00:00:00Z")
                        .lt("2024-01-01T00:00:00Z")
        );

        // Construir la agregación
        Aggregation aggregation = Aggregation.newAggregation(
                lookupOperation,
                projectionOperation,
                matchOperation
        );

        // Ejecutar la agregación y obtener resultados como un List<Map>
        List<Map> resultados = mongoTemplate.aggregate(aggregation, "clientes", Map.class).getMappedResults();

        model.addAttribute("resultados", resultados);
        return "resultados";
    }



    @GetMapping("/mostrarCostoConsumo")
    public String mostrarCostoConsumo(Model model, @RequestParam(name = "_id") String clienteId) {
        // Definir las operaciones de la agregación
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("ConsumosServicio")
                .localField("habitaciones.consumosServicios_id")
                .foreignField("_id")
                .as("consumosServiciosInfo");

        MatchOperation matchClienteId = Aggregation.match(Criteria.where("_id").is(clienteId));

        UnwindOperation unwindOperation = Aggregation.unwind("consumosServiciosInfo");

        MatchOperation matchFecha = Aggregation.match(
                Criteria.where("consumosServiciosInfo.fecha")
                        .gte("2023-01-01T00:00:00Z")
                        .lte("2023-12-31T23:59:59Z") // Ajusta según necesites
        );

        GroupOperation groupOperation = Aggregation.group("_id")
                .sum("consumosServiciosInfo.costo").as("totalCostoConsumo");

        // Construir la agregación
        Aggregation aggregation = Aggregation.newAggregation(
                matchClienteId,
                lookupOperation,
                unwindOperation,
                matchFecha,
                groupOperation
        );

        // Ejecutar la agregación y obtener resultados como un List<Map>
        List<Map> resultados = mongoTemplate.aggregate(aggregation, "clientes", Map.class).getMappedResults();

        model.addAttribute("resultados", resultados);
        return "costoConsumo";
    }


    @GetMapping("/excelentes")
    public List<Cliente> obtenerClientesExcelentes() {
        return clienteRepository.obtenerClientesExcelentes();
    }


}
