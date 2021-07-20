/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import controladores.AdopcionJpaController;
import controladores.MascotaJpaController;
import entidades.Adopcion;
import entidades.Cliente;
import entidades.Mascota;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import modelos.Carro;
import modelos.OperationResult;

/**
 *
 * @author Yunnicio
 */
public class AdopcionRepositorio {
    
    public static OperationResult<Boolean> Adoptar(HttpServletRequest request){
        try{
            System.out.println("TO ADOPTAR");
            Cliente cliente = ClienteRepositorio.getClienteSession(request);
            if(cliente == null) { return OperationResult.failure(false, "Necesita iniciar sesión"); }

            OperationResult<List<Mascota>> result = Carro.getCarro().getMascotas(request);
            
            if(!result.isSuccess()) { return OperationResult.failure(false, result.getMessage(), result.getDetailMessage()); }
            
            List<Mascota> mascotas = result.getResult();
            
            AdopcionJpaController controller = new AdopcionJpaController();
            
            // Chequear si el cliente tiene ya un límite de mascotas adoptadas
            List<Adopcion> adopciones = controller.findAdopcionEntities();
            long adopcionesCliente = adopciones.stream().filter(adopcion -> adopcion.getClienteId() == cliente.getId()).count();
            
            if(adopcionesCliente >= 3){
                return OperationResult.failure(false, "Ya ha llegado al límite de mascotas para adoptar (3 máximo)");
            }
            else if(adopcionesCliente + mascotas.size() > 3){
                return OperationResult.failure(false, "Solo puede añadir " + (adopcionesCliente + mascotas.size() - 3) + " mascota(s) más (3 mascotas máximo)");
            }
            
            for(Mascota mascota : mascotas){
                Adopcion adopcion = new Adopcion();
                adopcion.setFecha(new Date());
                adopcion.setClienteId(cliente.getId());
                adopcion.setMascotaId(mascota.getId());
                
                controller.create(adopcion);
            }
            
            System.out.println("SUCCESS");
            Carro.getCarro().clearCart(request);
            return OperationResult.success(true);
        } catch(Exception e ){
            System.out.println("ERROR ADOPCION: " + e.getMessage());
            return OperationResult.failure(false, "Ha ocurrido un error", e.getMessage());
        }
    }
    
    public static OperationResult<Boolean> QuitarAdopcion(Mascota mascota, Cliente cliente){
        try{
            
            OperationResult<List<Adopcion>> result = getAdopcionesOfCliente(cliente);
            if(!result.isSuccess()){ return OperationResult.failure(false, result.getMessage(), result.getDetailMessage()); }
            
            List<Adopcion> adopciones = result.getResult();
            adopciones = adopciones.stream().filter(adopcion -> adopcion.getMascotaId() == mascota.getId()).collect(Collectors.toList());
            
            if(adopciones.size() > 0){
                Adopcion adopcionRef = adopciones.get(0);
                AdopcionJpaController adopcionController = new AdopcionJpaController();
                adopcionController.destroy(adopcionRef.getId());
                return OperationResult.success(true);
            }
            
            return OperationResult.failure(false, "No se ha encontrado la adopción de esta mascota");
            
        }catch(Exception e){
            return OperationResult.failure(false, "Ha ocurrido un error", e.getMessage());
        }
    }
    
    public static OperationResult<List<Mascota>> getMascotasOfCliente(Cliente cliente){
        try{
            AdopcionJpaController adopcionController = new AdopcionJpaController();
            List<Adopcion> adopciones = adopcionController.findAdopcionEntities();
            MascotaJpaController mascotaController = new MascotaJpaController();
            List<Mascota> mascotas = mascotaController.findMascotaEntities();
            
            adopciones = adopciones.stream().filter(adopcion -> adopcion.getClienteId() == cliente.getId()).collect(Collectors.toList());
            
            System.out.println(adopciones);
            
            ArrayList<Mascota> toReturn = new ArrayList();
            for(Adopcion adopcion : adopciones){
                for(Mascota mascota : mascotas){
                    if(adopcion.getMascotaId() == mascota.getId()){
                        toReturn.add(mascota);
                    }
                }
            }
            
            return OperationResult.success(toReturn);
            
        } catch(Exception e){
            return OperationResult.failure(new ArrayList(), "Ha ocurrido un error", e.getMessage());
        }
    }
    
    public static OperationResult<List<Adopcion>> getAdopcionesOfCliente(Cliente cliente){
        try{
            AdopcionJpaController adopcionController = new AdopcionJpaController();
            List<Adopcion> adopciones = adopcionController.findAdopcionEntities();
            
            adopciones = adopciones.stream().filter(adopcion -> adopcion.getClienteId() == cliente.getId()).collect(Collectors.toList());
            
            return OperationResult.success(adopciones);
            
        } catch(Exception e){
            return OperationResult.failure(new ArrayList(), "Ha ocurrido un error", e.getMessage());
        }
    }
    
}
