package bean;

import dao.LeccionDao;
import dao.LeccionDaoImpl;
import dao.NivelDao;
import dao.NivelDaoImpl;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import modelo.Leccion;
import modelo.Nivel;

/**
 *
 * @author Roy
 */
@ManagedBean
@ViewScoped
public class NivelBean implements Serializable {

    private List<Nivel> niv;
    private Nivel selectedNivel;
    private String nivel;
    private String tema;
    private String estado;
    
    private Leccion lecc;
    private String leccion;
    private List<SelectItem> listaLecciones;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

    public NivelBean() {
        this.selectedNivel = new Nivel();
        this.niv = new ArrayList<Nivel>();


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

        listaLecciones = new ArrayList<SelectItem>();
        ArrayList<String> facu = this.listarLecciones();
        for (int i = 0; i < facu.size(); i++) {
            SelectItem item = new SelectItem();
            item.setValue(facu.get(i));
            item.setLabel(facu.get(i));
            listaLecciones.add(item);
        }

    }

    public void btnCreateNivel() {

        NivelDao nivelDao = new NivelDaoImpl();
        LeccionDao tipoUsuariodao = new LeccionDaoImpl();
        String msgs;
        
        Leccion lec = new Leccion();
        int id = tipoUsuariodao.retornarId(leccion);
        lec.setIdleccion(id);

        Nivel niv = new Nivel();
        niv.setLeccion(lec);
        niv.setNivel(nivel);
        niv.setTema(tema);
        niv.setEstado(estado);

        System.out.println("usuariorrrr: " + niv.getNivel()+ " " + niv.getTema()+ " " + niv.getEstado()+ " " + niv.getLeccion().getIdleccion());

        if (nivelDao.createU(niv)) {
            msgs = "Se creo correctamente el nivel";

            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

        } else {
            System.out.println("NOOOOOOO");
            msgs = "Error al crear el nivel";

            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }

    }

    public void btnUpdateNivel() {

        NivelDao nivelDao = new NivelDaoImpl();
        LeccionDao lecciondao = new LeccionDaoImpl();
        String msgs;

        Leccion lec = new Leccion();
        int id = lecciondao.retornarId(this.selectedNivel.getLeccion().getDescripcion());
        lec.setIdleccion(id);

        int idU = nivelDao.usuId(this.selectedNivel.getNivel());
        
        Nivel niv = new Nivel();
        niv.setIdnivel(idU);
        niv.setLeccion(lec);
        niv.setNivel(this.selectedNivel.getNivel());
        niv.setTema(this.selectedNivel.getTema());
        niv.setEstado(this.selectedNivel.getEstado());

        System.out.println("usuariorrrr: " + niv.getNivel()+ " " + niv.getTema()+ " " + niv.getEstado()+ " " + niv.getLeccion().getIdleccion());

        if (nivelDao.updateU(niv)) {
            
                System.out.println("SAOOOOOOO");
                msgs = "Se modifico correctamente el registro";
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            
        } else {
            System.out.println("NOOOOOOO");
            msgs = "Error al crear registro";

            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }


    }

    public void btnDeleteNivel() {

        NivelDao nivelDao = new NivelDaoImpl();
        String msgs;

        if (nivelDao.deleteU(this.selectedNivel)) {

            msgs = "Se elimino correctamente el registro";
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } else {

            msgs = "Error al eliminar registro";
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }


    }

    public ArrayList<String> listarLecciones() {
        LeccionDao leccionDao = new LeccionDaoImpl();

        List<Leccion> lecc = (List<Leccion>) new ArrayList();
        lecc = leccionDao.listarTipos();
        Iterator ite = lecc.iterator();
        Object nuevaf = new Object();
        ArrayList<String> nombreUsuTip = new ArrayList<String>();
        while (ite.hasNext()) {
            nuevaf = ite.next();
            nombreUsuTip.add(String.valueOf(nuevaf));
        }
        return nombreUsuTip;
    }

    public List<Nivel> getNiv() {
        NivelDao nivelDao=new NivelDaoImpl();
        this.niv=nivelDao.findAllUsuarios();
        return niv;
    }

    public void setNiv(List<Nivel> niv) {
        this.niv = niv;
    }

    public Nivel getSelectedNivel() {
        return selectedNivel;
    }

    public void setSelectedNivl(Nivel selectedNivel) {
        this.selectedNivel = selectedNivel;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nombre) {
        this.nivel = nombre;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Leccion getLecc() {
        return lecc;
    }

    public void setLecc(Leccion lecc) {
        this.lecc = lecc;
    }

    public String getLeccion() {
        return leccion;
    }

    public void setLeccion(String leccion) {
        this.leccion = leccion;
    }

    public List<SelectItem> getListaLecciones() {
        return listaLecciones;
    }

    public void setListaTiposDeUsuarios(List<SelectItem> listaLecciones) {
        this.listaLecciones = listaLecciones;
    }
    
}
