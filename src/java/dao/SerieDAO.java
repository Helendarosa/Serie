/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Serie;

/**
 *
 * @author Acer
 */
public class SerieDAO extends GenericDAO<Serie, Long>{
    
    public SerieDAO(){
        super(Serie.class);
    }
}
