package Grupo3.Verduleria.Repositorios;

import Grupo3.Verduleria.Entidades.productoUnidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositorioUnidad  extends JpaRepository<productoUnidad, String>{

}
