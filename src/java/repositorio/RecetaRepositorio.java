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
import modelos.OperationResult;
import modelos.RecetaResult;

/**
 *
 * @author Yunnicio
 */
public class RecetaRepositorio {
    
    public static OperationResult<Receta> encontrarReceta(int id){
        try{
            RecetaJpaController controller = new RecetaJpaController();
            return OperationResult.success(controller.findReceta(id));
        } catch(Exception e){
            return OperationResult.failure(null, "No se ha encontrado la receta", e.getMessage());
        }
    }
    
    public static OperationResult<List<RecetaResult>> obtenerRecetas(Cliente cliente){
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
            return OperationResult.success(resultados);
        } catch(Exception e){
            return OperationResult.failure(new ArrayList(), "No se han podido obtener las recetas", e.getMessage());
        }
    }
    
    public static OperationResult<List<RecetaResult>> busquedaRecetas(String textoBusqueda, Cliente cliente){
        try{
            OperationResult<List<RecetaResult>> result = obtenerRecetas(cliente);
            List<RecetaResult> recetas = result.getResult();
            if(textoBusqueda == null || textoBusqueda.isEmpty()){
                return OperationResult.success(recetas);
            }

            return OperationResult.success(recetas.stream().filter(
                receta -> 
                    receta.getReceta().getObjetivo().toLowerCase().contains(textoBusqueda.toLowerCase()) ||
                    Float.toString(receta.getReceta().getPeso()).contains(textoBusqueda)
            ).collect(Collectors.toList()));
        } catch(Exception e){
            return OperationResult.failure(new ArrayList(), "Ha ocurrido un error", e.getMessage());
        }
    }
    
    public static OperationResult<Boolean> editarReceta(int id, String nombre, String descripcion, String objetivo, float peso){
        Receta receta = new Receta(id);
        receta.setNombre(nombre);
        receta.setDescription(descripcion);
        receta.setObjetivo(objetivo);
        receta.setPeso(peso);
        return editarReceta(receta);
    }
    
    public static OperationResult<Boolean> editarReceta(Receta receta){
        try{
            RecetaJpaController controller = new RecetaJpaController();
            controller.edit(receta);
            return OperationResult.success(true);
        } catch(Exception e){
            return OperationResult.failure(false, "No se ha podido editar la receta", e.getMessage());
        }
    }
    
    public static OperationResult<Boolean> nuevaReceta(String nombre, String descripcion, String objetivo, float peso){
        Receta receta = new Receta();
        receta.setNombre(nombre);
        receta.setDescription(descripcion);
        receta.setObjetivo(objetivo);
        receta.setPeso(peso);
        return nuevaReceta(receta);
    }
    
    public static OperationResult<Boolean> nuevaReceta(Receta receta){
        try{
            RecetaJpaController controller = new RecetaJpaController();
            controller.create(receta);
            return OperationResult.success(true);
        } catch(Exception e){
            return OperationResult.failure(false, "No se ha podido crear la receta", e.getMessage());
        }
    }
    
    public static OperationResult<Boolean> eliminarReceta(int recetaId){
        try{
            RecetaJpaController controller = new RecetaJpaController();
            controller.destroy(recetaId);
            return OperationResult.success(true);
        } catch(Exception e){
            return OperationResult.failure(false, "No se ha podido eliminar la receta", e.getMessage());
        }
    }
    
    public static OperationResult<Boolean> addFavorito(int recetaId, Cliente cliente){
        try{
            RecetaFavorito favorito = new RecetaFavorito();
            favorito.setClienteId(cliente.getId());
            favorito.setRecetaId(recetaId);
            favorito.setFecha(new Date());
            
            RecetaFavoritoJpaController favoritoController = new RecetaFavoritoJpaController();
            favoritoController.create(favorito);
            return OperationResult.success(true);
        } catch(Exception e){
            return OperationResult.failure(false, "No se ha podido añadir a favorito", e.getMessage());
        }
    }
    
    public static OperationResult<Boolean> removeFavorite(int recetaId, Cliente cliente){
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
                return OperationResult.success(true);
            }
            return OperationResult.failure(false, "No se ha encontrado el favorito marcado");
        } catch(Exception e){
            return OperationResult.failure(false, "Ha ocurrido un error", e.getMessage());
        }
    }
}
