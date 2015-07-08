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
import modelo.Ciclo;
import modelo.Tipousuario;
import modelo.Usuario;
import modelo.Curso;
import org.hibernate.Session;

/**
 *
 * @author Roy
 */
public class CursoDaoImpl implements CursoDao, Serializable{

    @Override
    public List<Curso> findAllCursos() {
        
        List<Curso> listadoVeterinarias=null;
        
        Session session=HibernateUtil.getSessionFactory().openSession();
        String hql="FROM Curso";
        
        try{
            //session.beginTransaction();
            listadoVeterinarias = session.createQuery(hql).list();
            
            //session.beginTransaction().commit();
            
            for(Curso vet:listadoVeterinarias){
            
                System.out.println("UsuarIO: "+vet.getNombre()+" NOM: "+vet.getNombre()+" "+vet.getCiclo().getIdciclo()
                        +" "+vet.getCiclo().getNombre());
            }
            
            System.out.println("hOLA");
        }catch(Exception e){
            session.beginTransaction().rollback();
        }
        session.close();
        return listadoVeterinarias;
    }

    @Override
    public boolean createU(Curso curso) {
        boolean estado=false;
        System.out.println("usuario: "+curso.getNombre());
        
        Session sesion=HibernateUtil.getSessionFactory().openSession();
        
        try{
            sesion.getTransaction().begin();
            sesion.save(curso);
            estado=true;
            sesion.getTransaction().commit();
        
        }catch(Exception e){
            sesion.getTransaction().rollback();
        }
        sesion.close();
        
        return estado;
    }

    @Override
    public List<Curso> listarTipos() {
        List<Curso> listaCurso = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            listaCurso = session.createSQLQuery("Select nombre from Curso").list();
        } catch (Exception e) {
            session.beginTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return listaCurso;
    }
    
    
    
}
