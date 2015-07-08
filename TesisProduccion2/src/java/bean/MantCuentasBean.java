/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UsuariofacDao;
import dao.UsuariofacDaoImpl;
import dao.CursoDao;
import dao.CursoDaoImpl;
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
 * @author Roy
 */
@ManagedBean
@ViewScoped
public class MantCuentasBean {

    private List<Usuariofac> usuafac;
    private Usuariofac selectedUsuariofac;
    
    private String username;
    private String paasword;
    private String ncoleg;
    private String especi;
    
    private List<SelectItem> listaUsuariosfac;
    private List<SelectItem> listaCursos;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    boolean p=false;
    private String dniSelected;
    private String cursoSelected;
    
    private List<Curso> cursos;
    
    public MantCuentasBean() {
        
        this.selectedUsuariofac = new Usuariofac();
        this.usuafac = new ArrayList<Usuariofac>();


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
        
        listaCursos = new ArrayList<SelectItem>();
        ArrayList<String> facu = this.listarVeterinarias();
        for (int i = 0; i < facu.size(); i++) {
            SelectItem item = new SelectItem();
            item.setValue(facu.get(i));
            item.setLabel(facu.get(i));
            listaCursos.add(item);
        }
    }
    
    public void btnBuscar(){
        p=true;
    }
    
    public void btnSelected(){
    
        p=false;
    }
    
    public ArrayList<String> listarVeterinarias(){
    
        CursoDao cursoDao = new CursoDaoImpl();

        List<Curso> curso = (List<Curso>) new ArrayList();
        curso = cursoDao.listarTipos();
        Iterator ite = curso.iterator();
        Object nuevaf = new Object();
        ArrayList<String> nombreCurso = new ArrayList<String>();
        while (ite.hasNext()) {
            nuevaf = ite.next();
            nombreCurso.add(String.valueOf(nuevaf));
        }
        return nombreCurso;
        
    }
    
    

    public List<Usuariofac> getUsuafac() {
        UsuariofacDao usuariofacDao=new UsuariofacDaoImpl();
        this.usuafac=usuariofacDao.findAll();
        return usuafac;
    }

    public void setUsuafac(List<Usuariofac> usuafac) {
        this.usuafac = usuafac;
    }

    public Usuariofac getSelectedUsuariofac() {
        return selectedUsuariofac;
    }

    public void setSelectedUsuariofac(Usuariofac selectedUsuariofac) {
        this.selectedUsuariofac = selectedUsuariofac;
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

    public List<SelectItem> getListaUsuariosfac() {
        return listaUsuariosfac;
    }

    public void setListaUsuariosVet(List<SelectItem> listaUsuariosfac) {
        this.listaUsuariosfac = listaUsuariosfac;
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

    public List<Curso> getCursos() {
        CursoDao CursoDao=new CursoDaoImpl();
        this.cursos=CursoDao.findAllCursos();
        
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public String getCursoSelected() {
        return cursoSelected;
    }

    public void setCursoSelected(String cursoSelected) {
        this.cursoSelected = cursoSelected;
    }

    public List<SelectItem> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(List<SelectItem> listaCursos) {
        this.listaCursos = listaCursos;
    }
    
    
    
    
    
    
    
    
    
    
}
