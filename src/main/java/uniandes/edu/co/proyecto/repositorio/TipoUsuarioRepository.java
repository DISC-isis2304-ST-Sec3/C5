package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.TipoUsuario;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, String>{

     @Query(value = "SELECT * FROM tiposUsuario", nativeQuery = true)
    Collection<TipoUsuario> darTiposUsuarios();

    @Query(value = "SELECT * FROM tiposUsuario WHERE nombre= :nombre", nativeQuery = true)
    TipoUsuario darTipoUsuario(@Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tiposUsuario (nombre) VALUES ( :nombre )", nativeQuery = true)
    void insertarTiposUsuarios(@Param("nombre") String nombre);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE tiposUsuario SET nombre = :nombre_actualizado WHERE nombre = :nombre", nativeQuery = true)
    void actualizarTiposUsuarios(@Param("nombre") int nombre, @Param("nombre_actualizado") String nombre_actualizado);
    

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tiposUsuario WHERE nombre = :nombre", nativeQuery = true)
    void eliminarTipoUsuario(@Param("nombre") String nombre);
}
