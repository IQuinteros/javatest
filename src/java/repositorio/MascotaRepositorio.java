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
import modelos.OperationResult;

/**
 *
 * @author Yunnicio
 */
public class MascotaRepositorio {
    
    public static OperationResult<List<Mascota>> obtenerMascotasDisponibles(){
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
            
            return OperationResult.success(mascotas);
        } catch(Exception e){
            return OperationResult.failure(new ArrayList(), "Ha ocurrido un error", e.getMessage());
        }
    }
    
     public static OperationResult<List<Mascota>> obtenerMascotas(){
        try{
            MascotaJpaController mascotaController = new MascotaJpaController();
            List<Mascota> mascotas = mascotaController.findMascotaEntities();
            
            return OperationResult.success(mascotas);
        } catch(Exception e){
            return OperationResult.failure(new ArrayList(), "Ha ocurrido un error", e.getMessage());
        }
    }
    
    public static OperationResult<List<Mascota>> busquedaMascotasDisponibles(String textoBusqueda){
        OperationResult<List<Mascota>> result = obtenerMascotasDisponibles();
        
        if(!result.isSuccess()){ return OperationResult.failure(new ArrayList(), result.getMessage(), result.getDetailMessage()); }
        
        List<Mascota> mascotas = result.getResult();
        if(textoBusqueda == null || textoBusqueda.isEmpty()){
            return OperationResult.success(mascotas);
        }

        mascotas = mascotas.stream().filter(
                mascota -> 
                        mascota.getTipo().toLowerCase().contains(textoBusqueda.toLowerCase()) || 
                        mascota.getRaza().toLowerCase().contains(textoBusqueda.toLowerCase())
        ).collect(Collectors.toList());
        
        return OperationResult.success(mascotas);
    }
    
    public static OperationResult<List<Mascota>> busquedaMascotas(String textoBusqueda){
        OperationResult<List<Mascota>> result = obtenerMascotas();
        
        if(!result.isSuccess()){ return OperationResult.failure(new ArrayList(), result.getMessage(), result.getDetailMessage()); }
        
        List<Mascota> mascotas = result.getResult();
        if(textoBusqueda == null || textoBusqueda.isEmpty()){
            return OperationResult.success(mascotas);
        }

        mascotas = mascotas.stream().filter(
                mascota -> 
                        mascota.getTipo().toLowerCase().contains(textoBusqueda.toLowerCase()) || 
                        mascota.getRaza().toLowerCase().contains(textoBusqueda.toLowerCase())
        ).collect(Collectors.toList());
        
        return OperationResult.success(mascotas);
    }
    
    public static OperationResult<Mascota> encontrarMascota(int id){
        try{
            MascotaJpaController controller = new MascotaJpaController();
            return OperationResult.success(controller.findMascota(id));
        } catch(Exception e){
            return OperationResult.failure(null, "No se ha encontrado la mascota", e.getMessage());
        }
    }
    
    public static OperationResult<Boolean> borrarMascota(int id){
        try{
            MascotaJpaController controller = new MascotaJpaController();
            controller.destroy(id);
            return OperationResult.success(true);
        } catch(Exception e){
            return OperationResult.failure(false, "No se ha podido eliminar la mascota", e.getMessage());
        }
    }
    
    public static OperationResult<Boolean> nuevaMascota(String nombre, String raza, int edad, float peso, String foto, String tipo){
        Mascota mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setRaza(raza);
        mascota.setEdad(edad);
        mascota.setPeso(peso);
        mascota.setFoto(foto);
        mascota.setTipo(tipo);
        return nuevaMascota(mascota);
    }
    
    public static OperationResult<Boolean> nuevaMascota(Mascota mascota){
        try{
            MascotaJpaController controller = new MascotaJpaController();
            controller.create(mascota);
            return OperationResult.success(true);
        } catch(Exception e){
            return OperationResult.failure(false, "No se ha podido crear la mascota", e.getMessage());
        }
    }
    
    public static OperationResult<Boolean> editarMascota(int id, String raza, int edad, float peso, String foto, String tipo){
        try{
            OperationResult<Mascota> result = encontrarMascota(id);
            
            if(!result.isSuccess()){ return OperationResult.failure(false, result.getMessage(), result.getDetailMessage()); }
            
            Mascota mascota = result.getResult();
            mascota.setRaza(raza);
            mascota.setEdad(edad);
            mascota.setPeso(peso);
            mascota.setFoto(foto);
            mascota.setTipo(tipo);
            return editarMascota(mascota);
        } catch(Exception e){
            return OperationResult.failure(false, "No se ha encontrado la mascota", e.getMessage());
        }
    }
    
    public static OperationResult<Boolean> editarMascota(Mascota mascota){
        try{
            MascotaJpaController controller = new MascotaJpaController();
            controller.edit(mascota);
            return OperationResult.success(true);
        } catch(Exception e){
            return OperationResult.failure(false, "No se ha podido editar la mascota", e.getMessage());
        }
    }
    
}
