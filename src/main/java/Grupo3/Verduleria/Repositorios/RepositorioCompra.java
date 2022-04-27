package Grupo3.Verduleria.Repositorios;

import Grupo3.Verduleria.Entidades.Clientes;
import Grupo3.Verduleria.Entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositorioCompra extends JpaRepository <Compra, String> {

    
    @Query ("SELECT C FROM Compra C WHERE cliente= : cliente")
    public Compra buscarPorCliente(@Param("cliente") Clientes cliente);
}
