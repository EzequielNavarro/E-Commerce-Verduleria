package Grupo3.Verduleria.Repositorios;

import Grupo3.Verduleria.Entidades.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface repositorioClientes  extends JpaRepository<Clientes, String> {

}
