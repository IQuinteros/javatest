/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import entidades.Mascota;
import java.util.ArrayList;
import java.util.List;
import repositorio.MascotaRepositorio;

/**
 *
 * @author Yunnicio
 */
public class Carro {
    private ArrayList<Mascota> mascotas = new ArrayList();
    
    private static Carro singleton = new Carro();
    
    public static Carro getCarro(){
        return singleton;
    }
    
    public OperationResult<List<Mascota>> getMascotas(){
        OperationResult<List<Mascota>> result = MascotaRepositorio.obtenerMascotasDisponibles();
        
        if(!result.isSuccess()){ return OperationResult.failure(new ArrayList(), result.getMessage(), result.getDetailMessage()); }
        
        List<Mascota> disponibles = result.getResult();
        
        ArrayList<Mascota> finalResult = new ArrayList();
        for(Mascota enCarro : mascotas){
            Mascota found = null;
            for(Mascota disponible : disponibles){
                if(disponible.equals(enCarro)){
                    found = disponible;
                }
            }
            if(found != null){
                finalResult.add(found);
            }
        }
        
        mascotas = finalResult;
        System.out.println(mascotas);
        return OperationResult.success(mascotas);
    }
    
    public OperationResult<Boolean> addMascota(Mascota mascota){
        if(getCount() >= 3){ return OperationResult.failure(false, "Ha alcanzado el máximo de mascotas para añadir al carro (3 máximo)"); }
        if(existsMascota(mascota)){ return OperationResult.failure(false, "La mascota ya está agregada al carrito"); }
        return OperationResult.success(mascotas.add(mascota));
    }
    
    public boolean removeMascota(Mascota mascota){
        return mascotas.remove(mascota);
    }
    
    public int getCount(){
        return mascotas.size();
    }
    
    public boolean existsMascota(Mascota mascota){
        for(Mascota item : mascotas){
            if(item.equals(mascota)){
                return true;
            }
        }
        return false;
    }
    
    public void clearCart(){
        mascotas.clear();
    }
}
