/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import entidades.Mascota;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import repositorio.MascotaRepositorio;

/**
 *
 * @author Yunnicio
 */
public class Carro {    
    private static Carro singleton = new Carro();
    
    public static Carro getCarro(){
        return singleton;
    }
    
    public OperationResult<List<Mascota>> getMascotas(HttpServletRequest request){
        OperationResult<List<Mascota>> result = MascotaRepositorio.obtenerMascotasDisponibles();
        
        if(!result.isSuccess()){ return OperationResult.failure(new ArrayList(), result.getMessage(), result.getDetailMessage()); }
        
        List<Mascota> disponibles = result.getResult();
        
        HttpSession session = request.getSession(false);
        List<Integer> mascotas = (List<Integer>)session.getAttribute("carro");
        if(mascotas == null) { return OperationResult.failure(new ArrayList(), "Ha ocurrido un error"); }
        
        ArrayList<Mascota> finalResult = new ArrayList();
        for(int enCarro : mascotas){
            Mascota found = null;
            for(Mascota disponible : disponibles){
                if(disponible.getId() == enCarro){
                    found = disponible;
                }
            }
            if(found != null){
                finalResult.add(found);
            }
        }
        
        System.out.println(finalResult);
        return OperationResult.success(finalResult);
    }
    
    public OperationResult<Boolean> addMascota(Mascota mascota, HttpServletRequest request){
        if(getCount(request) >= 3){ return OperationResult.failure(false, "Ha alcanzado el máximo de mascotas para añadir al carro (3 máximo)"); }
        if(existsMascota(mascota, request)){ return OperationResult.failure(false, "La mascota ya está agregada al carrito"); }
        
        try{
            HttpSession session = request.getSession(false);
            List<Integer> mascotas = (List<Integer>)session.getAttribute("carro");
            if(mascotas == null) { mascotas = new ArrayList(); }

            mascotas.add(mascota.getId());
            session.setAttribute("carro", mascotas);
            return OperationResult.success(true);
        } catch(Exception e){
            return OperationResult.failure(false, "Ha ocurrido un error", e.getMessage());
        }
    }
    
    public boolean removeMascota(Mascota mascota, HttpServletRequest request){
        try{
            HttpSession session = request.getSession(false);
            List<Integer> mascotas = (List<Integer>)session.getAttribute("carro");
            if(mascotas == null) { return false; }
            mascotas.remove(mascota.getId());
            session.setAttribute("carro", mascotas);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
    public int getCount(HttpServletRequest request){
        try{
            HttpSession session = request.getSession(false);
            List<Integer> mascotas = (List<Integer>)session.getAttribute("carro");
            if(mascotas == null) { return 0; }
            return mascotas.size();
        } catch(Exception e){
            return 0;
        }
    }
    
    public boolean existsMascota(Mascota mascota, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        List<Integer> mascotas = (List<Integer>)session.getAttribute("carro");
        if(mascotas == null) { return false; }
        
        for(int item : mascotas){
            if(item == mascota.getId()){
                return true;
            }
        }
        return false;
    }
    
    public void clearCart(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.setAttribute("carro", new ArrayList());
    }
}
