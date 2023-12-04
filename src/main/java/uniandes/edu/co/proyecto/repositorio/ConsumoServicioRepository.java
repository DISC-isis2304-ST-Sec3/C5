package uniandes.edu.co.proyecto.repositorio;


import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.proyecto.modelo.ConsumoServicio;


public interface ConsumoServicioRepository extends MongoRepository<ConsumoServicio, String>{
    
    
}
