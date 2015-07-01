/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Util.HibernateUtil;
import java.util.List;
import modelo.Tipousuario;
import modelo.Curso;
import modelo.UsuarioCurso;
import org.hibernate.Session;

/**
 *
 * @author JESOFT
 */
public class veterinariaWorkDaoImpl implements veterinariaWorkDao{
    
    @Override
    public UsuarioCurso findAll(int id) {
        UsuarioCurso listadoVetWork=null;
        
        Session session=HibernateUtil.getSessionFactory().openSession();
        String hql="FROM UsuarioCurso where idusuariocurso='"+id+"'";
        
        try{
            //session.beginTransaction();
            listadoVetWork = (UsuarioCurso) session.createQuery(hql).uniqueResult();
            
            System.out.println("dsfsdf"+listadoVetWork.getCurso().getNombre());
            
            //session.beginTransaction().commit();
            System.out.println("hOLA");
        }catch(Exception e){
            session.beginTransaction().rollback();
        }
        session.close();
        return listadoVetWork;
    }

    
}
