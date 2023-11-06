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

    @Query(value = "SELECT * FROM usuarios WHERE idUser= :idUser", nativeQuery = true)
    Usuario darUsuario(@Param("idUser") Integer idUser);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (idUser, tipoDocumento, nombreUsuario, correo, TiposUsuarios_tipoUser) VALUES ( hotelAndes_sequence.nextval , :tipoDocumento, :nombreUsuario, :correo, :TiposUsuarios_tipoUser)", nativeQuery = true)
    void insertarUsuario(@Param("tipoDocumento") String tipoDocumento, @Param("nombreUsuario") String nombreUsuario, @Param("correo") String correo, @Param("TiposUsuarios_tipoUser") String TiposUsuarios_tipoUser);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET tipoDocumento = :tipoDocumento, nombreUsuario = :nombreUsuario, correo = :correo, TiposUsuarios_tipoUser = :TiposUsuarios_tipoUser WHERE idUser = :idUser", nativeQuery = true)
    void actualizarUsuario(@Param("idUser") Integer idUser, @Param("tipoDocumento") String tipoDocumento, @Param("nombreUsuario") String nombreUsuario, @Param("correo") String correo, @Param("TiposUsuarios_tipoUser") String TiposUsuarios_tipoUser);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuarios WHERE idUser = :idUser", nativeQuery = true)
    void eliminarUsuario(@Param("idUser") Integer idUser);
}
