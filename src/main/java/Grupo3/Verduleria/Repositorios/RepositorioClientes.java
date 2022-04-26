package Grupo3.Verduleria.Repositorios;

import Grupo3.Verduleria.Entidades.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositorioClientes  extends JpaRepository<Clientes, String> {
    
    @Query("SELECT c FROM Clientes c WHERE dni = :dni")
    public Clientes buscarPorDNI(@Param("dni")Long dni);
    
    @Query("SELECT c FROM Clientes c WHERE correo = :correo")
    public Clientes buscarPorMail(@Param("correo")String correo);
}
