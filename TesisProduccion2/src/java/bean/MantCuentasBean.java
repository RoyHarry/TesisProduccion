/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UsuarioVetDao;
import dao.UsuarioVetDaoImpl;
import dao.veterinariaDao;
import dao.veterinariaDaoImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import modelo.Usuariofac;
import modelo.Curso;

/**
 *
 * @author JESOFT
 */
@ManagedBean
@ViewScoped
public class MantCuentasBean {

    private List<Usuariofac> usuaVet;
    private Usuariofac selectedUsuarioVet;
    
    private String username;
    private String paasword;
    private String ncoleg;
    private String especi;
    
    private List<SelectItem> listaUsuariosVet;
    private List<SelectItem> listaVeterinarias;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    boolean p=false;
    private String dniSelected;
    private String vetSelected;
    
    private List<Curso> vets;
    
    public MantCuentasBean() {
        
        this.selectedUsuarioVet = new Usuariofac();
        this.usuaVet = new ArrayList<Usuariofac>();


        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("sesionUsuario") != null) {
            System.out.println("este hay " + httpServletRequest.getSession().getAttribute("sesionUsuario").toString());
        } else {
            if (httpServletRequest.getSession().isNew()) {
                try {
                    faceContext.getExternalContext().redirect("/TesisProduccion2/index.jsf");
                } catch (Exception e) {
                }
            } else {
                try {
                    faceContext.getExternalContext().redirect("/TesisProduccion2/index.jsf");
                } catch (Exception e) {
                }
            }
        }
        
        listaVeterinarias = new ArrayList<SelectItem>();
        ArrayList<String> facu = this.listarVeterinarias();
        for (int i = 0; i < facu.size(); i++) {
            SelectItem item = new SelectItem();
            item.setValue(facu.get(i));
            item.setLabel(facu.get(i));
            listaVeterinarias.add(item);
        }
    }
    
    public void btnBuscar(){
        p=true;
    }
    
    public void btnSelected(){
    
        p=false;
    }
    
    public ArrayList<String> listarVeterinarias(){
    
        veterinariaDao vetDao = new veterinariaDaoImpl();

        List<Curso> veteri = (List<Curso>) new ArrayList();
        veteri = vetDao.listarTipos();
        Iterator ite = veteri.iterator();
        Object nuevaf = new Object();
        ArrayList<String> nombreVet = new ArrayList<String>();
        while (ite.hasNext()) {
            nuevaf = ite.next();
            nombreVet.add(String.valueOf(nuevaf));
        }
        return nombreVet;
        
    }
    
    

    public List<Usuariofac> getUsuaVet() {
        UsuarioVetDao usuarioVetDao=new UsuarioVetDaoImpl();
        this.usuaVet=usuarioVetDao.findAll();
        return usuaVet;
    }

    public void setUsuaVet(List<Usuariofac> usuaVet) {
        this.usuaVet = usuaVet;
    }

    public Usuariofac getSelectedUsuarioVet() {
        return selectedUsuarioVet;
    }

    public void setSelectedUsuarioVet(Usuariofac selectedUsuarioVet) {
        this.selectedUsuarioVet = selectedUsuarioVet;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPaasword() {
        return paasword;
    }

    public void setPaasword(String paasword) {
        this.paasword = paasword;
    }

    public String getNcoleg() {
        return ncoleg;
    }

    public void setNcoleg(String ncoleg) {
        this.ncoleg = ncoleg;
    }

    public String getEspeci() {
        return especi;
    }

    public void setEspeci(String especi) {
        this.especi = especi;
    }

    public List<SelectItem> getListaUsuariosVet() {
        return listaUsuariosVet;
    }

    public void setListaUsuariosVet(List<SelectItem> listaUsuariosVet) {
        this.listaUsuariosVet = listaUsuariosVet;
    }

    public boolean isP() {
        return p;
    }

    public void setP(boolean p) {
        this.p = p;
    }

    public String getDniSelected() {
        return dniSelected;
    }

    public void setDniSelected(String dniSelected) {
        this.dniSelected = dniSelected;
    }

    public List<Curso> getVets() {
        veterinariaDao VetDao=new veterinariaDaoImpl();
        this.vets=VetDao.findAllVeterinarias();
        
        return vets;
    }

    public void setVets(List<Curso> vets) {
        this.vets = vets;
    }

    public String getVetSelected() {
        return vetSelected;
    }

    public void setVetSelected(String vetSelected) {
        this.vetSelected = vetSelected;
    }

    public List<SelectItem> getListaVeterinarias() {
        return listaVeterinarias;
    }

    public void setListaVeterinarias(List<SelectItem> listaVeterinarias) {
        this.listaVeterinarias = listaVeterinarias;
    }
    
    
    
    
    
    
    
    
    
    
}
