package Grupo3.Verduleria.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity

public class ProductoUnidad {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private Integer precio;
    private Integer unidad;

    public ProductoUnidad() {
    }

    public ProductoUnidad(String nombre, Integer precio, Integer unidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.unidad = unidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getUnidad() {
        return unidad;
    }

    public void setUnidad(Integer unidad) {
        this.unidad = unidad;
    }

    @Override
    public String toString() {
        return "productoUnidad{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", unidad=" + unidad + '}';
    }

    
    
}
