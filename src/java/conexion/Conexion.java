/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

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
public class Conexion {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestingPU");
    
    public static EntityManagerFactory getFactory(){
        return factory;
    }
}
