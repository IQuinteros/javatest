/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import controladores.ClienteJpaController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import entidades.Cliente;

/**
 *
 * @author Yunnicio
 */
public class ClienteRepositorio {
    public static Cliente getClienteSession(HttpServletRequest request){
        try{
            HttpSession sesion = request.getSession();
            Object object = sesion.getAttribute("cliente");
            return (Cliente)object;
        } catch(Exception e){
            return null;
        }
    }
    
    public static Cliente login(String email, String pass){
        ClienteJpaController controller = new ClienteJpaController();
        List<Cliente> clientes = controller.findClienteEntities();
        
        for(Cliente item : clientes){
            if(item instanceof Cliente){
                if(item.getEmail().equals(email) && item.getContraseña().equals(pass)){
                    return item;
                }
                else{
                    return null;
                }
            }
        }
        
        return null;
    }
    
    public static boolean login(String email, String pass, HttpServletRequest request){       
        try{
            Cliente result = login(email, pass);
            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("cliente", result);
            return true;
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("STACK:");
            for(StackTraceElement item : e.getStackTrace()){
                System.out.println("S: " + item);
            }
            return false;
        }
    }
    
    public static void logout(HttpServletRequest request){
        request.getSession().invalidate();
    }
}
