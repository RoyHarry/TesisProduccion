/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Leccion;
import modelo.Nivel;

/**
 *
 * @author Roy-Pc
 */
public interface LeccionDao {
    public List<Leccion> findAll();
    public boolean create(Leccion tipUsuario);
    public boolean update(Leccion tipUsuario);
    public boolean delete(Leccion tipUsuario);
    public List<Leccion> listarTipos();
    public int retornarId(String nombreLeccion);
}
