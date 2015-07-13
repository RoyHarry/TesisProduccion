/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import modelo.Nivel;
import org.hibernate.Session;

/**
 *
 * @author Roy-Pc
 */
public class NivelDaoImpl implements NivelDao, Serializable{

    @Override
    public List<Nivel> findAllUsuarios() {
        List<Nivel> listadoNiveles=null;
        
        Session session=HibernateUtil.getSessionFactory().openSession();
        String hql="FROM Nivel";
        
        try{
            //session.beginTransaction();
            listadoNiveles = session.createQuery(hql).list();
            
            //session.beginTransaction().commit();
            
            for(Nivel usu:listadoNiveles){
            
                System.out.println("UsuarIO: "+usu.getIdnivel()+" NOM: "+usu.getNivel()
                        +" TIPO: "+usu.getLeccion().getDescripcion());
            }
            
            System.out.println("hOLA");
        }catch(Exception e){
            session.beginTransaction().rollback();
        }
        session.close();
        return listadoNiveles;
    }

    @Override
    public boolean createU(Nivel usu) {
        boolean estado=false;
        System.out.println("nivel: "+usu.getNivel()+" "+usu.getTema()+" "+usu.getEstado());
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
    public boolean updateU(Nivel usu) {
        boolean estado=false;
        System.out.println("nivel: "+usu.getLeccion().getIdleccion()+" "+usu.getNivel()+" "+usu.getTema()+" "+usu.getEstado());
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
    public boolean deleteU(Nivel usu) {
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

    @Override
    public int usuId(String nom) {
        int id=-1;
        Nivel us=new Nivel();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            us= (Nivel) session.createQuery("from Usuario where nombre='"+nom+"'").uniqueResult();
            System.out.println("USUARIO: "+us.getNivel()+us.getLeccion().getDescripcion());
            id=us.getIdnivel();
        } catch (Exception e) {
            session.beginTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return id;
    }

    
    
}
