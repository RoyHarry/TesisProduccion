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
import modelo.Leccion;
import org.hibernate.Session;

/**
 *
 * @author Roy-Pc
 */
public class LeccionDaoImpl implements LeccionDao, Serializable {

    @Override
    public List<Leccion> findAll() {
        List<Leccion> listadoLecciones = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Leccion";

        try {
            //session.beginTransaction();
            listadoLecciones = session.createQuery(hql).list();

            //session.beginTransaction().commit();
            System.out.println("hOLA");
        } catch (Exception e) {
            session.beginTransaction().rollback();
        }
        session.close();
        return listadoLecciones;
    }

    @Override
    public boolean create(Leccion leccion) {
        boolean estado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            sesion.getTransaction().begin();
            sesion.save(leccion);
            estado = true;
            sesion.getTransaction().commit();

        } catch (Exception e) {
            sesion.getTransaction().rollback();
            System.out.println("ERROR AL CREAR");
        }
        sesion.close();

        return estado;
    }

    @Override
    public boolean update(Leccion leccion) {
        boolean estado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        //tipoUsuario.setIdtipo(10);
        System.out.println("TIPO" + leccion.getIdleccion()+ " " + leccion.getDescripcion()+ " " + leccion.getEstado()
                + " " + leccion.getPuntuacion());
        try {
            sesion.getTransaction().begin();

            //Tipousuario tipousuariodb=(Tipousuario) sesion.load(Tipousuario.class, tipoUsuario.getIdtipo());
            sesion.update(leccion);
            estado = true;
            sesion.getTransaction().commit();

        } catch (Exception e) {
            sesion.getTransaction().rollback();
        }
        sesion.close();

        return estado;
    }

    @Override
    public boolean delete(Leccion leccion) {
        boolean estado = false;
        System.out.println("IDDDD: " + leccion.getIdleccion());
        System.out.println("NOMBRE: " + leccion.getDescripcion());
        Session sesion = HibernateUtil.getSessionFactory().openSession();
//        Tipousuario tipoUsuario=new Tipousuario();
//        tipoUsuario.setIdtipo(id);

        try {
            sesion.getTransaction().begin();
            sesion.delete(leccion);
            estado = true;
            sesion.getTransaction().commit();

        } catch (Exception e) {
            sesion.getTransaction().rollback();
            System.out.println("ERROr" + e.getMessage());
        }
        sesion.close();

        return estado;
    }

    @Override
    public List<Leccion> listarTipos() {
        List<Leccion> listaLeccion = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            listaLeccion = session.createSQLQuery("Select descripcion from Leccion").list();
        } catch (Exception e) {
            session.beginTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return listaLeccion;
    }

    @Override
    public int retornarId(String nombreLeccion) {
        int id = -1;
        Leccion tp = new Leccion();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tp = (Leccion) session.createQuery("from Leccion where idleccion='" + nombreLeccion + "'").uniqueResult();
            System.out.println("Nivel: " + tp.getDescripcion()+ " " + tp.getIdleccion());
            id = tp.getIdleccion();
        } catch (Exception e) {
            session.beginTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        return id;
    }

}
