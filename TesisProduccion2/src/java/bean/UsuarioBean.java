package bean;

import dao.TipoUsuarioDao;
import dao.TipoUsuarioDaoImpl;
import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import modelo.Tipousuario;
import modelo.Usuario;
import modelo.Usuariofac;

/**
 *
 * @author Roy
 */
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private List<Usuario> usua;
    private Usuario selectedUsuario;
    private String nombre;
    private String apep;
    private String apem;
    private String fno;
    private String mail;
    private String dni;
    
    private Tipousuario tipusua;
    private String tipoUsuario;
    private List<SelectItem> listaTiposDeUsuarios;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;

    public UsuarioBean() {
        this.selectedUsuario = new Usuario();
        this.usua = new ArrayList<Usuario>();


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

        listaTiposDeUsuarios = new ArrayList<SelectItem>();
        ArrayList<String> facu = this.listarTiposUsuario();
        for (int i = 0; i < facu.size(); i++) {
            SelectItem item = new SelectItem();
            item.setValue(facu.get(i));
            item.setLabel(facu.get(i));
            listaTiposDeUsuarios.add(item);
        }

    }

    public void btnCreateUsuario() {

        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        TipoUsuarioDao tipoUsuariodao = new TipoUsuarioDaoImpl();
        String msgs;
        
        Tipousuario tpo = new Tipousuario();
        int id = tipoUsuariodao.retornarId(tipoUsuario);
        tpo.setIdtipousuario(id);

        Usuario us = new Usuario();
        us.setTipousuario(tpo);
        us.setNombre(nombre);
        us.setApellidop(apep);
        us.setApellidom(apem);
        us.setTelefono(fno);
        us.setEmail(mail);
        us.setDni(dni);

        System.out.println("usuariorrrr: " + us.getNombre() + " " + us.getApellidop()+ " " + us.getApellidom()+ " " + us.getTipousuario().getIdtipousuario());

        if (usuarioDao.createU(us)) {
            msgs = "Se creo correctamente la persona";

            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

        } else {
            System.out.println("NOOOOOOO");
            msgs = "Error al crear persona";

            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }

    }

    public void btnUpdateUsuario() {

        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        TipoUsuarioDao tipoUsuariodao = new TipoUsuarioDaoImpl();
        String msgs;

        Tipousuario tpo = new Tipousuario();
        int id = tipoUsuariodao.retornarId(this.selectedUsuario.getTipousuario().getNombre());
        tpo.setIdtipousuario(id);

        int idU = usuarioDao.usuId(this.selectedUsuario.getNombre());
        
        Usuario us = new Usuario();
        us.setIdusuario(idU);
        us.setTipousuario(tpo);
        us.setNombre(this.selectedUsuario.getNombre());
        us.setApellidop(this.selectedUsuario.getApellidop());
        us.setApellidom(this.selectedUsuario.getApellidom());
        us.setTelefono(this.selectedUsuario.getTelefono());
        us.setEmail(this.selectedUsuario.getEmail());
        us.setDni(this.selectedUsuario.getDni());

        System.out.println("usuariorrrr: " + us.getNombre() + " " + us.getApellidop()+ " " + us.getApellidom()+ " " + us.getTipousuario().getIdtipousuario());

        if (usuarioDao.updateU(us)) {
            
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

    public void btnDeleteUsuario() {

        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        String msgs;

        if (usuarioDao.deleteU(this.selectedUsuario)) {

            msgs = "Se elimino correctamente el registro";
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } else {

            msgs = "Error al eliminar registro";
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }


    }

    public ArrayList<String> listarTiposUsuario() {
        TipoUsuarioDao usuarioDao = new TipoUsuarioDaoImpl();

        List<Tipousuario> tipousu = (List<Tipousuario>) new ArrayList();
        tipousu = usuarioDao.listarTipos();
        Iterator ite = tipousu.iterator();
        Object nuevaf = new Object();
        ArrayList<String> nombreUsuTip = new ArrayList<String>();
        while (ite.hasNext()) {
            nuevaf = ite.next();
            nombreUsuTip.add(String.valueOf(nuevaf));
        }
        return nombreUsuTip;
    }

    public List<Usuario> getUsua() {
        UsuarioDao usuarioDao=new UsuarioDaoImpl();
        this.usua=usuarioDao.findAllUsuarios();
        return usua;
    }

    public void setUsua(List<Usuario> usua) {
        this.usua = usua;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApep() {
        return apep;
    }

    public void setApep(String apep) {
        this.apep = apep;
    }

    public String getApem() {
        return apem;
    }

    public void setApem(String apem) {
        this.apem = apem;
    }

    public String getFno() {
        return fno;
    }

    public void setFno(String fno) {
        this.fno = fno;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Tipousuario getTipusua() {
        return tipusua;
    }

    public void setTipusua(Tipousuario tipusua) {
        this.tipusua = tipusua;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<SelectItem> getListaTiposDeUsuarios() {
        return listaTiposDeUsuarios;
    }

    public void setListaTiposDeUsuarios(List<SelectItem> listaTiposDeUsuarios) {
        this.listaTiposDeUsuarios = listaTiposDeUsuarios;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    
}
