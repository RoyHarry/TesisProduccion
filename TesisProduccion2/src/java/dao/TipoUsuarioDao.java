/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Tipousuario;

/**
 *
 * @author Roy
 */
public interface TipoUsuarioDao {
    public List<Tipousuario> findAll();
    public boolean create(Tipousuario tipUsuario);
    public boolean update(Tipousuario tipUsuario);
    public boolean delete(Tipousuario tipUsuario);
    public List<Tipousuario> listarTipos();
    public int retornarId(String nombreTipo);
    
}
