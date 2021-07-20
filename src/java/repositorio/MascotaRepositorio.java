/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import controladores.AdopcionJpaController;
import controladores.MascotaJpaController;
import entidades.Adopcion;
import java.util.List;
import entidades.Mascota;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author Yunnicio
 */
public class MascotaRepositorio {
    
    public static List<Mascota> obtenerMascotasDisponibles(){
        try{
            MascotaJpaController mascotaController = new MascotaJpaController();
            AdopcionJpaController adopcionController = new AdopcionJpaController();
            List<Mascota> mascotas = mascotaController.findMascotaEntities();
            List<Adopcion> adopciones = adopcionController.findAdopcionEntities();
            
            ArrayList<Mascota> adoptados = new ArrayList();
            for(Adopcion adopcion : adopciones){
                for(Mascota mascota : mascotas){
                    if(mascota.getId() == adopcion.getMascotaId()){
                        adoptados.add(mascota);
                    }
                }
            }
            
            mascotas.removeAll(adoptados);
            
            return mascotas;
        } catch(Exception e){
            return new ArrayList<Mascota>();
        }
    }
    
    public static List<Mascota> busquedaMascotasDisponibles(String textoBusqueda){
        List<Mascota> mascotas = obtenerMascotasDisponibles();
        if(textoBusqueda == null || textoBusqueda.isEmpty()){
            return mascotas;
        }

        mascotas = mascotas.stream().filter(
                mascota -> 
                        mascota.getTipo().toLowerCase().contains(textoBusqueda.toLowerCase()) || 
                        mascota.getRaza().toLowerCase().contains(textoBusqueda.toLowerCase())
        ).collect(Collectors.toList());
        
        return mascotas;
    }
    
    public static Mascota encontrarMascota(int id){
        try{
            MascotaJpaController controller = new MascotaJpaController();
            return controller.findMascota(id);
        } catch(Exception e){
            return null;
        }
    }
    
    public static boolean borrarMascota(int id){
        try{
            MascotaJpaController controller = new MascotaJpaController();
            controller.destroy(id);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
}
