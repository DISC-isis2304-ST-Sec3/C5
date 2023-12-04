package uniandes.edu.co.proyecto.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.proyecto.modelo.HabitacionEmbedded;

public interface HabitacionRepository extends MongoRepository<HabitacionEmbedded, String> {
   
    List<HabitacionEmbedded> encontrarPorId(String id);
}