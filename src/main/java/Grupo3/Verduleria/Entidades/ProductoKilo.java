package Grupo3.Verduleria.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class ProductoKilo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private Integer precio;
    private Integer kilo;

    public ProductoKilo() {
    }

    public ProductoKilo(String nombre, Integer precio, Integer kilo) {
        this.nombre = nombre;
        this.precio = precio;
        this.kilo = kilo;
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

    public Integer getKilo() {
        return kilo;
    }

    public void setKilo(Integer kilo) {
        this.kilo = kilo;
    }

    @Override
    public String toString() {
        return "productoKilo{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", kilo=" + kilo + '}';
    }
    
    
}
