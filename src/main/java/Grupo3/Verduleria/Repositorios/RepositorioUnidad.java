package Grupo3.Verduleria.Repositorios;

import Grupo3.Verduleria.Entidades.ProductoUnidad;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 349c3419ccc26f57ed1fc12b08a6ffec70b08bfe
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD
public interface RepositorioUnidad extends JpaRepository<ProductoUnidad, String> {
=======
public interface RepositorioUnidad  extends JpaRepository<ProductoUnidad, String>{
>>>>>>> 349c3419ccc26f57ed1fc12b08a6ffec70b08bfe

    @Query("SELECT u FROM ProductoUnidad u WHERE nombre = :nombre")
    public List<ProductoUnidad> buscarPorNombre(@Param("nombre") String nombre);
}
