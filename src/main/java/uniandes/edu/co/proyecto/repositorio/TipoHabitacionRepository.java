package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.proyecto.modelo.TipoHabitacionEmbedded;

public interface TipoHabitacionRepository extends MongoRepository<TipoHabitacionEmbedded,String>{

    
}
