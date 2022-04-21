package Grupo3.Verduleria.Repositorios;

import Grupo3.Verduleria.Entidades.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositorioClientes  extends JpaRepository<Clientes, String> {

}
