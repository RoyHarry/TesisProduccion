/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.TipoUsuarioDao;
import dao.TipoUsuarioDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Tipousuario;

/**
 *
 * @author Roy
 */
@ManagedBean
@ViewScoped
public class TiposUsuariosBean implements Serializable{
    
    
    
    private List<Tipousuario> tipoUsuarios;
    private Tipousuario selectedTipoUsuario;

    /**
     * Creates a new instance of TiposUsuariosBean
     */
    
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    
    public TiposUsuariosBean() {
        this.selectedTipoUsuario=new Tipousuario();
        this.tipoUsuarios=new ArrayList<Tipousuario>();
        
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

    public List<Tipousuario> getTipoUsuarios() {
        TipoUsuarioDao usuarioDao=new TipoUsuarioDaoImpl();
        this.tipoUsuarios=usuarioDao.findAll();
        return tipoUsuarios;
    }

    public void setTipoUsuarios(List<Tipousuario> tipoUsuarios) {
        this.tipoUsuarios = tipoUsuarios;
    }

    public Tipousuario getSelectedTipoUsuario() {
        return selectedTipoUsuario;
    }

    public void setSelectedTipoUsuario(Tipousuario selectedTipoUsuario) {
        this.selectedTipoUsuario = selectedTipoUsuario;
    }
    
    public void btnCreateUsuario(){
    
        TipoUsuarioDao usuarioDao=new TipoUsuarioDaoImpl();
        String msgs;
        
        if(usuarioDao.create(this.selectedTipoUsuario)){
            msgs="Se creo correctamente el registro";
            
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }else{
            msgs="Error al crear registro";
            
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);            
        }
        
    }
    
    public void btnUpdateUsuario(){
    
        TipoUsuarioDao usuarioDao=new TipoUsuarioDaoImpl();
        String msgs;
        System.out.println("AAA: "+this.selectedTipoUsuario.getIdtipousuario());
        System.out.println("AAA: "+this.selectedTipoUsuario.getNombre());
//        this.selectedTipoUsuario.setIdtipo(this.selectedTipoUsuario.get);
        
        if(usuarioDao.update(this.selectedTipoUsuario)){
        
            msgs="Se modifico correctamente el registro";
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }else{
        
            msgs="Error al modificar registro";
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }
    
    public void btnDeleteUsuario(){
    
        TipoUsuarioDao usuarioDao=new TipoUsuarioDaoImpl();
        String msgs;
        
        System.out.println("dfgdf "+this.selectedTipoUsuario.getIdtipousuario());
        
        if(usuarioDao.delete(this.selectedTipoUsuario)){
        
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
