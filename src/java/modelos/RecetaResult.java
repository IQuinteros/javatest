/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import entidades.Receta;

/**
 *
 * @author Yunnicio
 */
public class RecetaResult {
    private Receta receta;
    private int numFavoritos;
    private boolean isFavoritoByCliente;

    public RecetaResult(Receta receta, int numFavoritos, boolean isFavoritoByCliente) {
        this.receta = receta;
        this.numFavoritos = numFavoritos;
        this.isFavoritoByCliente = isFavoritoByCliente;
    }

    public Receta getReceta() {
        return receta;
    }

    public int getNumFavoritos() {
        return numFavoritos;
    }

    public boolean isFavoritoByCliente() {
        return isFavoritoByCliente;
    }
    
    
}
