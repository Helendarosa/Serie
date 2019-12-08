/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Serie;

/**
 *
 * @author Acer
 */
public class SerieDAO extends GenericDAO<Serie, Long>{
    
    public SerieDAO(){
        super(Serie.class);
    }
    public List<Serie> listarClassificacao(Long id) {
      return em.createNamedQuery("Serie.findClassificacao").setParameter("classificacao", id).getResultList();
    }
    
    public List<Serie> listarGenero(Long id) {
      return em.createNamedQuery("Serie.findGenero").setParameter("genero", id).getResultList();
    }
    public List<Serie> listarPlataforma(Long id) {
      return em.createNamedQuery("Serie.findPlataforma").setParameter("plataforma", id).getResultList();
    }
}
