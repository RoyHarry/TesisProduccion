/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Util.HibernateUtil;
import java.util.List;
import modelo.Usuariofac;
import org.hibernate.Session;

/**
 *
 * @author JESOFT
 */
public class UsuarioVetDaoImpl implements UsuarioVetDao{
    
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
    public boolean create(Usuariofac usuarioVet) {
        boolean estado=false;
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            sesion.save(usuarioVet);
            estado=true;
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
        }
        sesion.close();
        
        return estado;
    }

    @Override
    public boolean update(Usuariofac usuarioVet) {
        boolean estado=false;
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            
            sesion.update(usuarioVet);
            estado=true;
            sesion.getTransaction().commit();
            System.out.println("CONNNNCHEEEE: "+usuarioVet.getIdusuariofac()+" --"+usuarioVet.getUser()+"--"
                    +usuarioVet.getPass());
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
        }
        sesion.close();
        
        return estado;
    }
    
    @Override
    public boolean delete(Usuariofac usuarioVet) {
        boolean estado=false;
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            
            sesion.delete(usuarioVet);
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
