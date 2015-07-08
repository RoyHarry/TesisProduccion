/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.CicloDao;
import dao.CicloDaoImpl;
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
import modelo.Ciclo;
import modelo.Usuario;
import modelo.Curso;

/**
 *
 * @author Roy
 */
@ManagedBean
@ViewScoped
public class MantCursosBean {

    private List<Curso> cursos;
    private Curso selectedCurso;
    
    private String nombre;
    private String direccion;
    private String telefono;
    private String mail;
    private String web;
    
    private String distr;
    private List<SelectItem> listaCiclos;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    
    /**
     * Creates a new instance of MantVeterinarias
     */
    public MantCursosBean() {
        
        this.selectedCurso = new Curso();
        this.cursos = new ArrayList<Curso>();


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

    }
    
//    public void btnCreateUsuario() {
//
//        UsuarioDao usuarioDao = new UsuarioDaoImpl();
//        TipoUsuarioDao tipoUsuariodao = new TipoUsuarioDaoImpl();
//        String msgs;
//        
//        Distrito vetDao = new Distrito();
//        int id = tipoUsuariodao.retornarId(tipoUsuario);
//        tpo.setIdtipo(id);
//
//        Veterinaria vet = new Veterinaria();
//        vet.setNombre(nombre);
//        vet.setDireccion(direccion);
//        vet.setTelefono(Integer.parseInt(telefono));
//        vet.setEmail(mail);
//        vet.setWeb(web);
//        
//
//        System.out.println("usuariorrrr: ")
//
//        if (usuarioDao.createU(us)) {
//            msgs = "Se creo correctamente la persona";
//
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//
//        } else {
//            System.out.println("NOOOOOOO");
//            msgs = "Error al crear persona";
//
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs, null);
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//        }
//
//    }
    
    
    
    
    public ArrayList<String> listarCiclo() {
        CicloDao distDao = new CicloDaoImpl();

        List<Ciclo> ciclos = (List<Ciclo>) new ArrayList();
        ciclos = distDao.listarCiclos();
        Iterator ite = ciclos.iterator();
        Object nuevaf = new Object();
        ArrayList<String> nombreDist = new ArrayList<String>();
        while (ite.hasNext()) {
            nuevaf = ite.next();
            nombreDist.add(String.valueOf(nuevaf));
        }
        return nombreDist;
    }

    public List<Curso> getCursos() {
        CursoDao cursoDao=new CursoDaoImpl();
        this.cursos=cursoDao.findAllCursos();
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Curso getSelectedCurso() {
        return selectedCurso;
    }

    public void setSelectedCurso(Curso selectedCurso) {
        this.selectedCurso = selectedCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public List<SelectItem> getListaCiclos() {
        listaCiclos = new ArrayList<SelectItem>();
        ArrayList<String> ciclo = this.listarCiclo();
        for (int i = 0; i < ciclo.size(); i++) {
            SelectItem item = new SelectItem();
            item.setValue(ciclo.get(i));
            item.setLabel(ciclo.get(i));
            listaCiclos.add(item);
        }
        return listaCiclos;
    }

    public void setListaCiclos(List<SelectItem> listaCiclos) {
        this.listaCiclos = listaCiclos;
    }

    public String getDistr() {
        return distr;
    }

    public void setDistr(String distr) {
        this.distr = distr;
    }
    
    
    
    
}
