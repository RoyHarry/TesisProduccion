/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Util.HibernateUtil;
import modelo.Tipousuario;
import modelo.Usuario;
import modelo.Usuariofac;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Roy
 */
public class prueba {
    
    public prueba() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        Usuariofac usu=new Usuariofac();
        usu.setUser("sec");
        usu.setPass("sec");
        Usuario usua=new Usuario();
        usua.setNombre("Jes");
        usua.setApellidom("cah");
        usua.setApellidop("auq");
        Tipousuario tp=new Tipousuario();
        tp.setIdtipousuario(6);
        usua.setTipousuario(tp);
        usu.setUsuario(usua);
        
        try{
            sesion.getTransaction().begin();
            sesion.save(usu);
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
            System.out.println("ERROR AL CREAR");
        }
        sesion.close();
        
     
     }
     @Test
     public boolean updateU(Usuario usu) {
         usu.setNombre("LUIS");
         usu.setApellidop("BENAVIDES");
         usu.setApellidom("ACEVEDO");
         usu.setTelefono("45464");
         usu.setEmail("LUIS@GMAIL.COM");
        boolean estado=false;
        System.out.println("usuario: "+usu.getTipousuario().getIdtipousuario()+" "+usu.getNombre()+" "+usu.getApellidop()+" "+usu.getApellidom());
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            sesion.update(usu);
            estado=true;
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
        }
        sesion.close();
        
        return estado;
    }
}