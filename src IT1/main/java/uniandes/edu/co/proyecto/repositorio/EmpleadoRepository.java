package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Empleado;
import uniandes.edu.co.proyecto.modelo.Usuario;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{
    
    @Query(value = "SELECT * FROM empleados", nativeQuery = true)
    Collection<Empleado> darEmpleados();

    @Query(value = "SELECT * FROM empleados WHERE idEmpleado= :idEmpleado", nativeQuery = true)
    Empleado darEmpleado(@Param("idEmpleado") int idEmpleado);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO empleados (idEmpleado, TiposUsuarios_tipoUser) VALUES ( hotelAndes_sequence.nextval , :TiposUsuarios_tipoUser)", nativeQuery = true)
    void insertarEmpleado(@Param("TiposUsuarios_tipoUser") String TiposUsuarios_tipoUser);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE empleados SET TiposUsuarios_tipoUser = :TiposUsuarios_tipoUser WHERE idEmpleado = :idEmpleado", nativeQuery = true)
    void actualizarEmpleado(@Param("idEmpleado") Integer idEmpleado, @Param("TiposUsuarios_tipoUser") String TiposUsuarios_tipoUser);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM empleados WHERE idEmpleado = :idEmpleado", nativeQuery = true)
    void eliminarEmpleado(@Param("idEmpleado") Integer idUser);
}
