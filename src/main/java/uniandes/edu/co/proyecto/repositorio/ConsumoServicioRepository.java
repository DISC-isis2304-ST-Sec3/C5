package uniandes.edu.co.proyecto.repositorio;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.proyecto.modelo.ConsumoServicio;


public interface ConsumoServicioRepository extends MongoRepository<ConsumoServicio, String>{
    
    List<ConsumoServicio> encontrarPorId(String id);
}
