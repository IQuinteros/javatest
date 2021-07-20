/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import controladores.RecetaFavoritoJpaController;
import controladores.RecetaJpaController;
import entidades.Cliente;
import entidades.Receta;
import entidades.RecetaFavorito;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import modelos.RecetaResult;

/**
 *
 * @author Yunnicio
 */
public class RecetaRepositorio {
    
    public static Receta encontrarReceta(int id){
        try{
            RecetaJpaController controller = new RecetaJpaController();
            return controller.findReceta(id);
        } catch(Exception e){
            return null;
        }
    }
    
    public static List<RecetaResult> obtenerRecetas(Cliente cliente){
        try{
            RecetaJpaController recetaController = new RecetaJpaController();
            RecetaFavoritoJpaController favoritoController = new RecetaFavoritoJpaController();
            
            List<Receta> recetas = recetaController.findRecetaEntities();
            List<RecetaFavorito> favoritos = favoritoController.findRecetaFavoritoEntities();
            
            ArrayList<RecetaResult> resultados = new ArrayList();
            for(Receta receta : recetas){
                List<RecetaFavorito> favoritosReceta = favoritos.stream().filter(favorito -> favorito.getRecetaId() == receta.getId()).collect(Collectors.toList());
                boolean clienteFavorito = false;
                if(cliente != null){
                    clienteFavorito = favoritosReceta.stream().filter(favorito -> favorito.getClienteId() == cliente.getId()).count() > 0;
                }
                resultados.add(new RecetaResult(receta, favoritosReceta.size(), clienteFavorito));
            }
            return resultados;
        } catch(Exception e){
            return new ArrayList();
        }
    }
    
    public static List<RecetaResult> busquedaRecetas(String textoBusqueda, Cliente cliente){
        try{
            List<RecetaResult> recetas = obtenerRecetas(cliente);
            if(textoBusqueda == null || textoBusqueda.isEmpty()){
                return recetas;
            }

            return recetas.stream().filter(
                receta -> 
                    receta.getReceta().getObjetivo().toLowerCase().contains(textoBusqueda.toLowerCase()) ||
                    Float.toString(receta.getReceta().getPeso()).contains(textoBusqueda)
            ).collect(Collectors.toList());
        } catch(Exception e){
            return new ArrayList();
        }
    }
    
    public static boolean editarReceta(int id, String nombre, String descripcion, String objetivo, float peso){
        Receta receta = new Receta(id);
        receta.setNombre(nombre);
        receta.setDescription(descripcion);
        receta.setObjetivo(objetivo);
        receta.setPeso(peso);
        return nuevaReceta(receta);
    }
    
    public static boolean editarReceta(Receta receta){
        try{
            RecetaJpaController controller = new RecetaJpaController();
            controller.edit(receta);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
    public static boolean nuevaReceta(String nombre, String descripcion, String objetivo, float peso){
        Receta receta = new Receta();
        receta.setNombre(nombre);
        receta.setDescription(descripcion);
        receta.setObjetivo(objetivo);
        receta.setPeso(peso);
        return nuevaReceta(receta);
    }
    
    public static boolean nuevaReceta(Receta receta){
        try{
            RecetaJpaController controller = new RecetaJpaController();
            controller.create(receta);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
    public static boolean addFavorito(int recetaId, Cliente cliente){
        try{
            RecetaFavorito favorito = new RecetaFavorito();
            favorito.setClienteId(cliente.getId());
            favorito.setRecetaId(recetaId);
            favorito.setFecha(new Date());
            
            RecetaFavoritoJpaController favoritoController = new RecetaFavoritoJpaController();
            favoritoController.create(favorito);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
    public static boolean removeFavorite(int recetaId, Cliente cliente){
        try{
            RecetaFavoritoJpaController favoritoController = new RecetaFavoritoJpaController();
            List<RecetaFavorito> favoritos = favoritoController.findRecetaFavoritoEntities();
            
            favoritos = favoritos.stream().filter(
                favorito ->
                    favorito.getRecetaId() == recetaId &&
                    favorito.getClienteId() == cliente.getId()
            ).collect(Collectors.toList());
            
            if(favoritos.size() > 0){
                favoritoController.destroy(favoritos.get(0).getId());
                return true;
            }
             return false;
        } catch(Exception e){
            return false;
        }
    }
}
