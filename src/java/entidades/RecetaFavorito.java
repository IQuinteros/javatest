/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yunnicio
 */
@Entity
@Table(name = "receta_favorito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecetaFavorito.findAll", query = "SELECT r FROM RecetaFavorito r"),
    @NamedQuery(name = "RecetaFavorito.findById", query = "SELECT r FROM RecetaFavorito r WHERE r.id = :id"),
    @NamedQuery(name = "RecetaFavorito.findByRecetaId", query = "SELECT r FROM RecetaFavorito r WHERE r.recetaId = :recetaId"),
    @NamedQuery(name = "RecetaFavorito.findByClienteId", query = "SELECT r FROM RecetaFavorito r WHERE r.clienteId = :clienteId"),
    @NamedQuery(name = "RecetaFavorito.findByFecha", query = "SELECT r FROM RecetaFavorito r WHERE r.fecha = :fecha")})
public class RecetaFavorito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "receta_id")
    private int recetaId;
    @Basic(optional = false)
    @Column(name = "cliente_id")
    private int clienteId;
    @Basic(optional = false)
    @Column(name = "fecha")
    private int fecha;

    public RecetaFavorito() {
    }

    public RecetaFavorito(Integer id) {
        this.id = id;
    }

    public RecetaFavorito(Integer id, int recetaId, int clienteId, int fecha) {
        this.id = id;
        this.recetaId = recetaId;
        this.clienteId = clienteId;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(int recetaId) {
        this.recetaId = recetaId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecetaFavorito)) {
            return false;
        }
        RecetaFavorito other = (RecetaFavorito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.RecetaFavorito[ id=" + id + " ]";
    }
    
}
