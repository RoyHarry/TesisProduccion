package modelo;
// Generated 28/06/2015 01:18:53 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Vocabulario generated by hbm2java
 */
@Entity
@Table(name="vocabulario"
    ,catalog="tesisproduccion2"
)
public class Vocabulario  implements java.io.Serializable {


     private Integer idvocabulario;
     private DetalleLeccion detalleLeccion;
     private String palabra;
     private String palabratraducida;

    public Vocabulario() {
    }

    public Vocabulario(DetalleLeccion detalleLeccion, String palabra, String palabratraducida) {
       this.detalleLeccion = detalleLeccion;
       this.palabra = palabra;
       this.palabratraducida = palabratraducida;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idvocabulario", unique=true, nullable=false)
    public Integer getIdvocabulario() {
        return this.idvocabulario;
    }
    
    public void setIdvocabulario(Integer idvocabulario) {
        this.idvocabulario = idvocabulario;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="detalle_leccion_iddetalleleccion", nullable=false)
    public DetalleLeccion getDetalleLeccion() {
        return this.detalleLeccion;
    }
    
    public void setDetalleLeccion(DetalleLeccion detalleLeccion) {
        this.detalleLeccion = detalleLeccion;
    }

    
    @Column(name="palabra", nullable=false, length=45)
    public String getPalabra() {
        return this.palabra;
    }
    
    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    
    @Column(name="palabratraducida", nullable=false, length=45)
    public String getPalabratraducida() {
        return this.palabratraducida;
    }
    
    public void setPalabratraducida(String palabratraducida) {
        this.palabratraducida = palabratraducida;
    }




}


