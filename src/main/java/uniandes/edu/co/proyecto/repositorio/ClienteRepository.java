package uniandes.edu.co.proyecto.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.proyecto.modelo.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String>{

        List<Cliente> encontrarPorId(String id);

        @Aggregation(pipeline = {
            "{ $lookup: { from: 'ReservasAlojamientos', localField: 'reservaAlojamiento_id', foreignField: '_id', as: 'reservasAlojamientos' } }",
            "{ $unwind: '$reservasAlojamientos' }",
            "{ $group: { _id: '$_id', clienteInfo: { $first: { _id: '$_id', acompañantes: '$acompañantes', cuenta: '$cuenta', reservaTerminada: '$reservaTerminada', reservaAlojamiento_id: '$reservaAlojamiento_id', habitaciones: '$habitaciones' } }, reservas: { $push: { fechaEntrada: '$reservasAlojamientos.fechaEntrada' } } } }",
            "{ $project: { _id: 1, clienteInfo: 1, clienteExcelente: { $cond: { if: { $and: [ { $isArray: '$reservas' }, { $gte: [{ $size: '$reservas' }, 2] }, { $lt: [ { $arrayElemAt: ['$reservas.fechaEntrada', 0] }, { $arrayElemAt: ['$reservas.fechaEntrada', 1] } ] }, { $lte: [ { $arrayElemAt: ['$reservas.fechaEntrada', 0] }, { $add: [{ $arrayElemAt: ['$reservas.fechaEntrada', 1] }, 3 * 30 * 24 * 60 * 60 * 1000] } ] } ] }, then: true, else: false } } } }",
            "{ $match: { clienteExcelente: true } }"
    })
    List<Cliente> obtenerClientesExcelentes();
        
}




