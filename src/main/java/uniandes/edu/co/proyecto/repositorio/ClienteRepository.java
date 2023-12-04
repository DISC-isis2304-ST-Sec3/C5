package uniandes.edu.co.proyecto.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.proyecto.modelo.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String>{

        List<Cliente> encontrarPorId(String id);

        
}




