/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.LeccionDao;
import dao.LeccionDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Leccion;

/**
 *
 * @author Roy
 */
@ManagedBean
@ViewScoped
public class LeccionBean implements Serializable{
    
    
    
    private List<Leccion> leccion;
    private Leccion selectedLeccion;

    /**
     * Creates a new instance of TiposUsuariosBean
     */
    
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    
    public LeccionBean() {
        this.selectedLeccion=new Leccion();
        this.leccion=new ArrayList<Leccion>();
        
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest)faceContext.getExternalContext().getRequest();
        if(httpServletRequest.getSession().getAttribute("sesionUsuario")!=null){
            System.out.println("este hay "+httpServletRequest.getSession().getAttribute("sesionUsuario").toString());
        }else{
            if ( httpServletRequest.getSession().isNew() ){
                try{faceContext.getExternalContext().redirect("/TesisProduccion2/index.jsf");}catch(Exception e){}
            }else{
                try{faceContext.getExternalContext().redirect("/TesisProduccion2/index.jsf");}catch(Exception e){}
            }
        }
    }

    public List<Leccion> getLeccion() {
        LeccionDao leccionDao=new LeccionDaoImpl();
        this.leccion=leccionDao.findAll();
        return leccion;
    }

    public void setLeccion(List<Leccion> leccion) {
        this.leccion = leccion;
    }

    public Leccion getSelectedLeccion() {
        return selectedLeccion;
    }

    public void setSelectedLeccion(Leccion selectedLeccion) {
        this.selectedLeccion = selectedLeccion;
    }
    
    public void btnCreateLeccion(){
    
        LeccionDao leccionDao=new LeccionDaoImpl();
        String msgs;
        
        if(leccionDao.create(this.selectedLeccion)){
            msgs="Se creo correctamente el registro";
            
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }else{
            msgs="Error al crear registro";
            
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);            
        }
        
    }
    
    public void btnUpdateLeccion(){
    
        LeccionDao nivelDao=new LeccionDaoImpl();
        String msgs;
        System.out.println("AAA: "+this.selectedLeccion.getIdleccion());
        System.out.println("AAA: "+this.selectedLeccion.getDescripcion());
//        this.selectedTipoUsuario.setIdtipo(this.selectedTipoUsuario.get);
        
        if(nivelDao.update(this.selectedLeccion)){
        
            msgs="Se modifico correctamente el registro";
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }else{
        
            msgs="Error al modificar registro";
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }
    
    public void btnDeleteLeccion(){
    
        LeccionDao leccionDao=new LeccionDaoImpl();
        String msgs;
        
        System.out.println("dfgdf "+this.selectedLeccion.getIdleccion());
        
        if(leccionDao.delete(this.selectedLeccion)){
        
            msgs="Se elimino correctamente el registro";
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }else{
        
            msgs="Error al eliminar registro";
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        
        
    }
    
    
}
