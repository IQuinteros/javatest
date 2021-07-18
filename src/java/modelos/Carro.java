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
    
    public List<Mascota> getMascotas(){
        List<Mascota> disponibles = MascotaRepositorio.obtenerMascotasDisponibles();
        
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
        return mascotas;
    }
    
    public boolean addMascota(Mascota mascota){
        if(getCount() >= 3){ return false; }
        if(existsMascota(mascota)){ return false; }
        return mascotas.add(mascota);
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
