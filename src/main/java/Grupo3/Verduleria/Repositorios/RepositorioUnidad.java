package Grupo3.Verduleria.Repositorios;

import Grupo3.Verduleria.Entidades.ProductoUnidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositorioUnidad  extends JpaRepository<ProductoUnidad, String>{

}
