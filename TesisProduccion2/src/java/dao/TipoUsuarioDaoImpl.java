/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.Tipousuario;
import modelo.Usuariofac;
import org.hibernate.Session;

/**
 *
 * @author Roy
 */
public class TipoUsuarioDaoImpl implements TipoUsuarioDao, Serializable{
    
    @Override
    public List<Tipousuario> findAll() {
        List<Tipousuario> listadoTipoUsuarios=null;
        
        Session session=HibernateUtil.getSessionFactory().openSession();
        String hql="FROM Tipousuario";
        
        try{
            //session.beginTransaction();
            listadoTipoUsuarios = session.createQuery(hql).list();
            
            //session.beginTransaction().commit();
            System.out.println("hOLA");
        }catch(Exception e){
            session.beginTransaction().rollback();
        }
        session.close();
        return listadoTipoUsuarios;
    }
    
    @Override
    public boolean create(Tipousuario tipoUsuario) {
        boolean estado=false;
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            sesion.save(tipoUsuario);
            estado=true;
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
            System.out.println("ERROR AL CREAR");
        }
        sesion.close();
        
        return estado;
    }
    
    @Override
    public boolean update(Tipousuario tipoUsuario) {
        boolean estado=false;
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        //tipoUsuario.setIdtipo(10);
        System.out.println("TIPO"+tipoUsuario.getIdtipousuario()+" "+tipoUsuario.getNombre()+" "+tipoUsuario.getDescripcion()
                +" "+tipoUsuario.getPrioridad());
        try{
            sesion.getTransaction().begin();
            
            //Tipousuario tipousuariodb=(Tipousuario) sesion.load(Tipousuario.class, tipoUsuario.getIdtipo());
            
            sesion.update(tipoUsuario);
            estado=true;
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
        }
        sesion.close();
        
        return estado;
    }
    
    @Override
    public boolean delete(Tipousuario tipoUsuario) {
        boolean estado=false;
        System.out.println("IDDDD: "+tipoUsuario.getIdtipousuario());
        System.out.println("NOMBRE: "+tipoUsuario.getNombre());
        Session sesion=HibernateUtil.getSessionFactory().openSession();
//        Tipousuario tipoUsuario=new Tipousuario();
//        tipoUsuario.setIdtipo(id);
        
        try{
            sesion.getTransaction().begin();
            sesion.delete(tipoUsuario);
            estado=true;
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
            System.out.println("ERROr"+e.getMessage());
        }
        sesion.close();
        
        return estado;
    }

    @Override
    public List<Tipousuario> listarTipos() {
        List<Tipousuario> listaFacultad = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            listaFacultad = session.createSQLQuery("Select nombre from Tipousuario").list();
        } catch (Exception e) {
            session.beginTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return listaFacultad;
    }

    @Override
    public int retornarId(String nombreTipo) {
        int id=-1;
        Tipousuario tp=new Tipousuario();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tp= (Tipousuario) session.createQuery("from Tipousuario where nombre='"+nombreTipo+"'").uniqueResult();
            System.out.println("TIPOSUSU: "+tp.getNombre()+" "+tp.getIdtipousuario());
            id=tp.getIdtipousuario();
        } catch (Exception e) {
            session.beginTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return id;
    }
    
}
