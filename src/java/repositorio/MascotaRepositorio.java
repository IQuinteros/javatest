/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import controladores.MascotaJpaController;
import java.util.List;
import modelos.Mascota;

/**
 *
 * @author Yunnicio
 */
public class MascotaRepositorio {
    
    public static List<Mascota> obtenerMascotasDisponibles(){
        MascotaJpaController controller = new MascotaJpaController();
        List<Mascota> mascotas = controller.findMascotaEntities();
        return mascotas;
    }
    
}
