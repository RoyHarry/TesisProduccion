/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Usuariofac;

/**
 *
 * @author JESOFT
 */
public interface UsuarioVetDao {
    public boolean create(Usuariofac usuarioVet);
    public boolean update(Usuariofac usuarioVet);
    public boolean delete(Usuariofac usuarioVet);
    public List<Usuariofac> findAll();
    
}
