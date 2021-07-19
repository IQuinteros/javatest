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

/**
 *
 * @author Yunnicio
 */
public class AdopcionRepositorio {
    
    public static boolean Adoptar(HttpServletRequest request){
        try{
            System.out.println("TO ADOPTAR");
            Cliente cliente = ClienteRepositorio.getClienteSession(request);
            if(cliente == null) { return false; }
            
            List<Mascota> mascotas = Carro.getCarro().getMascotas();
            
            AdopcionJpaController controller = new AdopcionJpaController();
            
            // Chequear si el cliente tiene ya un límite de mascotas adoptadas
            List<Adopcion> adopciones = controller.findAdopcionEntities();
            long adopcionesCliente = adopciones.stream().filter(adopcion -> adopcion.getClienteId() == cliente.getId()).count();
            
            if(adopcionesCliente >= 3){
                return false;
            }
            
            for(Mascota mascota : mascotas){
                Adopcion adopcion = new Adopcion();
                adopcion.setFecha(new Date());
                adopcion.setClienteId(cliente.getId());
                adopcion.setMascotaId(mascota.getId());
                
                controller.create(adopcion);
            }
            
            System.out.println("SUCCESS");
            Carro.getCarro().clearCart();
            return true;
        } catch(Exception e ){
            System.out.println("ERROR ADOPCION: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean QuitarAdopcion(Mascota mascota, Cliente cliente){
        try{
            
            List<Adopcion> adopciones = getAdopcionesOfCliente(cliente);
            adopciones = adopciones.stream().filter(adopcion -> adopcion.getMascotaId() == mascota.getId()).collect(Collectors.toList());
            
            if(adopciones.size() > 0){
                Adopcion adopcionRef = adopciones.get(0);
                AdopcionJpaController adopcionController = new AdopcionJpaController();
                adopcionController.destroy(adopcionRef.getId());
                return true;
            }
            
            return false;
            
        }catch(Exception e){
            return false;
        }
    }
    
    public static List<Mascota> getMascotasOfCliente(Cliente cliente){
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
            return toReturn;
            
        } catch(Exception e){
            return new ArrayList();
        }
    }
    
    public static List<Adopcion> getAdopcionesOfCliente(Cliente cliente){
        try{
            AdopcionJpaController adopcionController = new AdopcionJpaController();
            List<Adopcion> adopciones = adopcionController.findAdopcionEntities();
            
            adopciones = adopciones.stream().filter(adopcion -> adopcion.getClienteId() == cliente.getId()).collect(Collectors.toList());
            
            return adopciones;
            
        } catch(Exception e){
            return new ArrayList();
        }
    }
    
}
