/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.listeners;

import entidades.Mascota;
import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author Yunnicio
 */
public class MascotaListener {
    
    @PreUpdate
    @PrePersist
    public void setLastUpdate(Mascota mascota){
        mascota.setLastUpdate(new Date());
    }
}
