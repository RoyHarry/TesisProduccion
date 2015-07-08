/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.Session;
import Util.HibernateUtil;
import java.io.Serializable;
import modelo.Tipousuario;
import modelo.Usuario;
import modelo.Usuariofac;

/**
 *
 * @author Roy
 */
public class UsuarioDaoImpl implements UsuarioDao, Serializable{

    @Override
    public Usuariofac findByUsuario(Usuariofac usuario) {
        
        Usuariofac usu=null;
        
        Session session=HibernateUtil.getSessionFactory().openSession();
        String hql="FROM Usuariofac WHERE user='"+usuario.getUser()+"'";
        
        try{
            //session.beginTransaction();
            usu=(Usuariofac) session.createQuery(hql).uniqueResult();
            //session.beginTransaction().commit();
            //System.out.println("hOLA");
            System.out.println("UsuarIO: "+usu.getUser()+" NOM: "+usu.getUsuario().getNombre());
        }catch(Exception e){
            session.beginTransaction().rollback();
        }
        session.close();
        return usu;
    }

    @Override
    public Usuariofac login(Usuariofac usuario) {
        Usuariofac usu=this.findByUsuario(usuario);
        
        if(usu!=null){
            if(!usuario.getPass().equals(usu.getPass())){
                usu=null;
            }
        }
        return usu;
    }

    @Override
    public List<Usuariofac> findAll() {
        List<Usuariofac> listadoUsuarios=null;
        
        Session session=HibernateUtil.getSessionFactory().openSession();
        String hql="FROM UsuarioVet";
        
        try{
            //session.beginTransaction();
            listadoUsuarios = session.createQuery(hql).list();
            
            //session.beginTransaction().commit();
            
            for(Usuariofac usu:listadoUsuarios){
            
                System.out.println("UsuarIO: "+usu.getUser()+" NOM: "+usu.getUsuario().getNombre()
                        +" TIPO: "+usu.getUsuario().getTipousuario().getNombre());
            }
            
            System.out.println("hOLA");
        }catch(Exception e){
            session.beginTransaction().rollback();
        }
        session.close();
        return listadoUsuarios;
    }

    @Override
    public boolean create(Usuariofac usuariofac) {
        boolean estado=false;
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            sesion.save(usuariofac);
            estado=true;
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
        }
        sesion.close();
        
        return estado;
    }

    @Override
    public boolean update(Usuariofac usuariofac) {
        boolean estado=false;
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            
            sesion.update(usuariofac);
            estado=true;
            sesion.getTransaction().commit();
            System.out.println("CONNNNCHEEEE: "+usuariofac.getIdusuariofac()+" --"+usuariofac.getUser()+"--"
                    +usuariofac.getPass());
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
        }
        sesion.close();
        
        return estado;
    }
    
    @Override
    public boolean delete(Usuariofac usuariofac) {
        boolean estado=false;
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            
            sesion.delete(usuariofac);
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
    public boolean createU(Usuario usu) {
        boolean estado=false;
        System.out.println("usuario: "+usu.getNombre()+" "+usu.getApellidop()+" "+usu.getApellidom());
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            sesion.save(usu);
            estado=true;
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
        }
        sesion.close();
        
        return estado;
    }

    @Override
    public int usuId(String nom) {
        int id=-1;
        Usuario us=new Usuario();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            us= (Usuario) session.createQuery("from Usuario where nombre='"+nom+"'").uniqueResult();
            System.out.println("USUARIO: "+us.getNombre()+us.getTipousuario().getNombre());
            id=us.getIdusuario();
        } catch (Exception e) {
            session.beginTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return id;
    }

    @Override
    public boolean updateU(Usuario usu) {
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

    @Override
    public int usuvetId(String nom) {
        int id=-1;
        Usuariofac us=new Usuariofac();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            us= (Usuariofac) session.createQuery("from Usuariofac where user='"+nom+"'").uniqueResult();
            System.out.println("USUARIO: "+us.getIdusuariofac()+us.getUser()+" "+us.getPass());
            id=us.getIdusuariofac();
        } catch (Exception e) {
            session.beginTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return id;
    }

    @Override
    public List<Usuario> findAllUsuarios() {
       List<Usuario> listadoUsuarios=null;
        
        Session session=HibernateUtil.getSessionFactory().openSession();
        String hql="FROM Usuario";
        
        try{
            //session.beginTransaction();
            listadoUsuarios = session.createQuery(hql).list();
            
            //session.beginTransaction().commit();
            
            for(Usuario usu:listadoUsuarios){
            
                System.out.println("UsuarIO: "+usu.getIdusuario()+" NOM: "+usu.getNombre()
                        +" TIPO: "+usu.getTipousuario().getNombre());
            }
            
            System.out.println("hOLA");
        }catch(Exception e){
            session.beginTransaction().rollback();
        }
        session.close();
        return listadoUsuarios;
    }

    @Override
    public boolean deleteU(Usuario usu) {
        boolean estado=false;
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            
            sesion.delete(usu);
            estado=true;
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
            System.out.println("ERROr"+e.getMessage());
        }
        sesion.close();
        
        return estado;
    }
    
}
