/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import modelos.Mascota;

/**
 *
 * @author Yunnicio
 */
public class MascotasDAO {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestingPU");
    EntityManager manager = factory.createEntityManager();
    
    public List<Mascota> listar(){
        TypedQuery query = manager.createNamedQuery("Mascota.findAll", Mascota.class);
        return query.getResultList();
    }
}
