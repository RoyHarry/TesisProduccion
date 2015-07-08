/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import dao.UsuarioCursoDao;
import dao.UsuarioCursoDaoImpl;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import modelo.Usuariofac;
import modelo.UsuarioCurso;

/**
 *
 * @author Roy
 */
//@Named(value="loginBean")
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    
    /**
     * Creates a new instance of loginBean
     */
    private String usernamexxx;
    private String password;
    private UsuarioDao usuarioDao;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    int id;
    
    /////datos usuario//
    private String nombre;
    private String apellidos;
    
    private String nombreCurso;

    public LoginBean() {

        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();

        this.usuarioDao = new UsuarioDaoImpl();


    }

    public String login(){
        Usuariofac user = new Usuariofac();
        user.setUser(usernamexxx);
        user.setPass(password);

        Usuariofac uf = new Usuariofac();
        uf = this.usuarioDao.login(user);
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        
        this.setNombre(uf.getUsuario().getNombre());
        this.setApellidos(uf.getUsuario().getApellidop()+" "+uf.getUsuario().getApellidom());
        
        UsuarioCursoDao uc=new UsuarioCursoDaoImpl();
        UsuarioCurso vet=new UsuarioCurso();
        vet=uc.findAll(uf.getIdusuariofac());
        try {
            if (uf != null) {
                
                nombreCurso=vet.getCurso().getNombre();
                
                FacesContext faceContex = FacesContext.getCurrentInstance();
                httpServletRequest = (HttpServletRequest) faceContex.getExternalContext().getRequest();
                httpServletRequest.getSession().setAttribute("sesionUsuario", uf.getUser());
                return "inicio";

            } else {

                try {
                    System.out.println("HOLIII BOLIII BY ROYYYY");

//                    FacesContext faceContextt = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Login Error", "Usuario y/o clave incorrectos"));

                    return "index";
                } catch (Exception e) {
                    System.out.println("NOOOOO");
                    System.out.println("ERROR2 " + e.getMessage());
                    return "index";
                }
            }
        } catch (Exception e) {
            return "index";
        }


    }

    public String logOut() {

        httpServletRequest.removeAttribute("sesionUsuario");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";
    }

    public String getUsernamexxx() {
        return usernamexxx;
    }

    public void setUsernamexxx(String usernamexxx) {
        this.usernamexxx = usernamexxx;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
    
    

}
