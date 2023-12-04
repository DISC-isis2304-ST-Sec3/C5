package uniandes.edu.co.proyecto.repositorio;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.proyecto.modelo.HabitacionEmbedded;

public interface HabitacionRepository extends MongoRepository<HabitacionEmbedded, String> {
   
    List<HabitacionEmbedded> encontrarPorId(String id);
     @Aggregation("{ $lookup: { from: 'consumosServicios', localField: 'consumosServicios', foreignField: '_id', as: 'consumosServicioInfo' } }, { $unwind: { path: '$consumosServicioInfo', preserveNullAndEmptyArrays: true } }, { $match: { 'consumosServicioInfo.fecha': { $gte: '2023-01-01', $lt: '2024-01-01' } } }, { $project: { numeroHab: 1, costoConsumoServicio: { $cond: { if: { $and: [ { $gte: ['$consumosServicioInfo.fecha', ISODate('2023-01-01')] }, { $lt: ['$consumosServicioInfo.fecha', ISODate('2024-01-01')] } ] }, then: '$consumosServicioInfo.costo', else: 0 } } } }, { $group: { _id: '$numeroHab', totalCostos: { $sum: '$costoConsumoServicio' } } }")
    AggregationResults<Map> obtenerCostosConsumoPorHabitacion();

}