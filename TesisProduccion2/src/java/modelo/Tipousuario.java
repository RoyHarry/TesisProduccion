package modelo;
// Generated 28/06/2015 01:18:53 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tipousuario generated by hbm2java
 */
@Entity
@Table(name="tipousuario"
    ,catalog="tesisproduccion2"
)
public class Tipousuario  implements java.io.Serializable {


     private Integer idtipousuario;
     private String nombre;
     private String descripcion;
     private String prioridad;
     private Set usuarios = new HashSet(0);

    public Tipousuario() {
    }

	
    public Tipousuario(String nombre, String prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }
    public Tipousuario(String nombre, String descripcion, String prioridad, Set usuarios) {
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.prioridad = prioridad;
       this.usuarios = usuarios;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idtipousuario", unique=true, nullable=false)
    public Integer getIdtipousuario() {
        return this.idtipousuario;
    }
    
    public void setIdtipousuario(Integer idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    
    @Column(name="nombre", nullable=false, length=45)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="descripcion", length=45)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="prioridad", nullable=false, length=45)
    public String getPrioridad() {
        return this.prioridad;
    }
    
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="tipousuario")
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }




}


