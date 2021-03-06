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
 * HistorialNotas generated by hbm2java
 */
@Entity
@Table(name="historial_notas"
    ,catalog="tesisproduccion2"
)
public class HistorialNotas  implements java.io.Serializable {


     private Integer idhistorialnotas;
     private Usuariofac usuariofac;
     private String resumen;
     private String observacion;

    public HistorialNotas() {
    }

    public HistorialNotas(Usuariofac usuariofac, String resumen, String observacion) {
       this.usuariofac = usuariofac;
       this.resumen = resumen;
       this.observacion = observacion;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idhistorialnotas", unique=true, nullable=false)
    public Integer getIdhistorialnotas() {
        return this.idhistorialnotas;
    }
    
    public void setIdhistorialnotas(Integer idhistorialnotas) {
        this.idhistorialnotas = idhistorialnotas;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usuariofac_idusuariofac", nullable=false)
    public Usuariofac getUsuariofac() {
        return this.usuariofac;
    }
    
    public void setUsuariofac(Usuariofac usuariofac) {
        this.usuariofac = usuariofac;
    }

    
    @Column(name="resumen", nullable=false, length=45)
    public String getResumen() {
        return this.resumen;
    }
    
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    
    @Column(name="observacion", nullable=false, length=45)
    public String getObservacion() {
        return this.observacion;
    }
    
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }




}


