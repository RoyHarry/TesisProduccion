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
 * UsuarioCurso generated by hbm2java
 */
@Entity
@Table(name="usuario_curso"
    ,catalog="tesisproduccion2"
)
public class UsuarioCurso  implements java.io.Serializable {


     private Integer idusuariocurso;
     private Curso curso;
     private Usuariofac usuariofac;

    public UsuarioCurso() {
    }

    public UsuarioCurso(Curso curso, Usuariofac usuariofac) {
       this.curso = curso;
       this.usuariofac = usuariofac;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idusuariocurso", unique=true, nullable=false)
    public Integer getIdusuariocurso() {
        return this.idusuariocurso;
    }
    
    public void setIdusuariocurso(Integer idusuariocurso) {
        this.idusuariocurso = idusuariocurso;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="curso_idcurso", nullable=false)
    public Curso getCurso() {
        return this.curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usuariofac_idusuariofac", nullable=false)
    public Usuariofac getUsuariofac() {
        return this.usuariofac;
    }
    
    public void setUsuariofac(Usuariofac usuariofac) {
        this.usuariofac = usuariofac;
    }




}


