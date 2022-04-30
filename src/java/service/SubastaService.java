/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import dao.SubastaFacade;
import entity.Subasta;


/**
 *
 * @author Gorpax
 */
@Stateless
public class SubastaService {
    @EJB SubastaFacade subFC;

    public Subasta buscarSubasta(int id) {
        return this.subFC.find(id);
    }
    
    
    
    
}
