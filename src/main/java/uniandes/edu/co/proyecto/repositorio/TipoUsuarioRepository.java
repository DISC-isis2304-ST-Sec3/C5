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

     @Query(value = "SELECT * FROM tiposUsuarios", nativeQuery = true)
    Collection<TipoUsuario> darTiposUsuarios();

    @Query(value = "SELECT * FROM tiposUsuarios WHERE tipoUser= :tipoUser", nativeQuery = true)
    TipoUsuario darTipoUsuario(@Param("tipoUser") String tipoUser);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tiposUsuarios (tipoUser) VALUES ( :NuevoTipoUsuario)", nativeQuery = true)
    void insertarTiposUsuarios(@Param("NuevoTipoUsuario") String NuevoTipoUsuario);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE tiposUsuarios SET tipoUser = :tipoUser WHERE tipoUser = :tipoUser", nativeQuery = true)
    void actualizarTiposUsuarios(@Param("tipoUser") String tipoUser);
    

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tiposUsuarios WHERE tipoUser = :tipoUser", nativeQuery = true)
    void eliminarTipoUsuario(@Param("tipoUser") String tipoUser);
}
