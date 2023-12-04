package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.proyecto.modelo.ReservaAlojamiento;

public interface ReservaAlojamientoRepository extends MongoRepository<ReservaAlojamiento,String>{

  
}
