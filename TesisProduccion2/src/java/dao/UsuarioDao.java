/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Usuario;
import modelo.Usuariofac;

/**
 *
 * @author Roy
 */
public interface UsuarioDao {
    
    public Usuariofac findByUsuario(Usuariofac usuario);
    public Usuariofac login(Usuariofac usuario);
    public List<Usuariofac> findAll();
    public List<Usuario> findAllUsuarios();
    public boolean create(Usuariofac usuariofac);
    public boolean update(Usuariofac usuariofac);
    public boolean delete(Usuariofac usuariofac);
    
    public boolean createU(Usuario usu);
    public boolean updateU(Usuario usu);
    public boolean deleteU(Usuario usu);
    public int usuId(String nom);
    public int usuvetId(String nom);
    
    
}
