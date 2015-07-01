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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name="usuario"
    ,catalog="tesisproduccion2"
)
public class Usuario  implements java.io.Serializable {


     private Integer idusuario;
     private Tipousuario tipousuario;
     private String nombre;
     private String apellidop;
     private String apellidom;
     private String telefono;
     private String email;
     private String dni;
     private Set usuariofacs = new HashSet(0);

    public Usuario() {
    }

	
    public Usuario(Tipousuario tipousuario, String nombre, String apellidop, String apellidom, String telefono, String email, String dni) {
        this.tipousuario = tipousuario;
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.telefono = telefono;
        this.email = email;
        this.dni = dni;
    }
    public Usuario(Tipousuario tipousuario, String nombre, String apellidop, String apellidom, String telefono, String email, String dni, Set usuariofacs) {
       this.tipousuario = tipousuario;
       this.nombre = nombre;
       this.apellidop = apellidop;
       this.apellidom = apellidom;
       this.telefono = telefono;
       this.email = email;
       this.dni = dni;
       this.usuariofacs = usuariofacs;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idusuario", unique=true, nullable=false)
    public Integer getIdusuario() {
        return this.idusuario;
    }
    
    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="tipousuario_idtipousuario", nullable=false)
    public Tipousuario getTipousuario() {
        return this.tipousuario;
    }
    
    public void setTipousuario(Tipousuario tipousuario) {
        this.tipousuario = tipousuario;
    }

    
    @Column(name="nombre", nullable=false, length=45)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="apellidop", nullable=false, length=45)
    public String getApellidop() {
        return this.apellidop;
    }
    
    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    
    @Column(name="apellidom", nullable=false, length=45)
    public String getApellidom() {
        return this.apellidom;
    }
    
    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    
    @Column(name="telefono", nullable=false, length=45)
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    @Column(name="email", nullable=false, length=45)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="dni", nullable=false, length=45)
    public String getDni() {
        return this.dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set getUsuariofacs() {
        return this.usuariofacs;
    }
    
    public void setUsuariofacs(Set usuariofacs) {
        this.usuariofacs = usuariofacs;
    }




}

