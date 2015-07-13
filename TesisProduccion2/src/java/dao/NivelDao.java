/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Nivel;

/**
 *
 * @author Roy-Pc
 */
public interface NivelDao {
    public List<Nivel> findAllUsuarios();    
    public boolean createU(Nivel usu);
    public boolean updateU(Nivel usu);
    public boolean deleteU(Nivel usu);
    public int usuId(String nom);
}
