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
import org.hibernate.Session;

/**
 *
 * @author Roy
 */
public class CicloDaoImpl implements CicloDao, Serializable{
    @Override
    public List<Ciclo> listarCiclos() {
        
        List<Ciclo> listarCiclos = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            listarCiclos = session.createSQLQuery("Select nombre from Ciclo").list();
            
//            for(int i=0; ){
//            
//                System.out.println("Id: "+dist.getIddistrito()+" NOM: "+dist.getNombre());
//            }
        } catch (Exception e) {
            session.beginTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return listarCiclos;
    }
    
}
