package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import uniandes.edu.co.proyecto.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    @Query(value = "SELECT * FROM usuarios", nativeQuery = true)
    Collection<Usuario> darUsuarios();

    @Query(value = "SELECT * FROM usuarios WHERE cedula= :cedula", nativeQuery = true)
    Usuario darUsuario(@Param("cedula") int cedula);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (cedula, tipoDocumento, nombreUsuario, correo, TiposUsuarios_nombre) VALUES ( hotelAndes_sequence.nextval , :tipoDocumento, :nombreUsuario, :correo, :TiposUsuarios_nombre)", nativeQuery = true)
    void insertarUsuarios(@Param("tipoDocumento") String tipoDocumento, @Param("nombreUsuario") String nombreUsuario, @Param("correo") String correo, @Param("TiposUsuarios_nombre") String TiposUsuarios_nombre);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET tipoDocumento = :tipoDocumento, nombreUsuario = :nombreUsuario, correo = :correo, TiposUsuarios_nombre = :TiposUsuarios_nombre WHERE cedula = :cedula", nativeQuery = true)
    void actualizarUsuarios(@Param("cedula") int cedula, @Param("tipoDocumento") String tipoDocumento, @Param("nombreUsuario") String nombreUsuario, @Param("correo") String correo, @Param("TiposUsuarios_nombre") String TiposUsuarios_nombre);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuarios WHERE cedula = :cedula", nativeQuery = true)
    void eliminarUsuario(@Param("cedula") int cedula);
}
