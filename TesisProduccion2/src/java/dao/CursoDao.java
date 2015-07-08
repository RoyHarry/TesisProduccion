/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Ciclo;
import modelo.Curso;

/**
 *
 * @author Roy
 */
public interface CursoDao {
    
    public List<Curso> findAllCursos();
    public boolean createU(Curso curso);
    public List<Curso> listarTipos();
    
}
