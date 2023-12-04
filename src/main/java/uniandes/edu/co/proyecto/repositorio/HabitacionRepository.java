package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import uniandes.edu.co.proyecto.modelo.HabitacionEmbedded;

public interface HabitacionRepository extends MongoRepository<HabitacionEmbedded, String> {
   
}