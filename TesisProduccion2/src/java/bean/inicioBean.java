/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Roy
 */
@ManagedBean
@ViewScoped
public class inicioBean implements Serializable{
    private String var="";
    
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

    /**
     * Creates a new instance of inicioBean
     */
    public inicioBean() {
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

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }
    
    
    
}
