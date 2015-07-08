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
 * @author Roy
 */
public interface UsuariofacDao {
    public boolean create(Usuariofac usuariofac);
    public boolean update(Usuariofac usuariofac);
    public boolean delete(Usuariofac usuariofac);
    public List<Usuariofac> findAll();
    
}
