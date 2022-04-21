package Grupo3.Verduleria.Repositorios;

import Grupo3.Verduleria.Entidades.ProductoUnidad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUnidad extends JpaRepository<ProductoUnidad, String> {

    @Query("SELECT u FROM ProductoUnidad u WHERE nombre = :nombre")
    public List<ProductoUnidad> buscarPorNombre(@Param("nombre") String nombre);
}
