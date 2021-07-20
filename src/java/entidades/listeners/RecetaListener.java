/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.listeners;

import entidades.Receta;
import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author Yunnicio
 */
public class RecetaListener {
    
    @PreUpdate
    @PrePersist
    public void setLastUpdate(Receta receta){
        receta.setLastUpdate(new Date());
    }
}
