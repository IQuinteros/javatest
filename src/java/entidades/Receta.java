/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import entidades.listeners.RecetaListener;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yunnicio
 */
@Entity
@EntityListeners({RecetaListener.class})
@Table(name = "receta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receta.findAll", query = "SELECT r FROM Receta r"),
    @NamedQuery(name = "Receta.findById", query = "SELECT r FROM Receta r WHERE r.id = :id"),
    @NamedQuery(name = "Receta.findByNombre", query = "SELECT r FROM Receta r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Receta.findByObjetivo", query = "SELECT r FROM Receta r WHERE r.objetivo = :objetivo"),
    @NamedQuery(name = "Receta.findByPeso", query = "SELECT r FROM Receta r WHERE r.peso = :peso")})
public class Receta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "objetivo")
    private String objetivo;
    @Basic(optional = false)
    @Column(name = "peso")
    private float peso;
    @Basic(optional = false)
    @Column(name = "lastUpdate")
    @Temporal(TemporalType.DATE)
    private Date lastUpdate;

    public Receta() {
    }

    public Receta(Integer id) {
        this.id = id;
    }

    public Receta(Integer id, String nombre, String description, String objetivo, float peso) {
        this.id = id;
        this.nombre = nombre;
        this.description = description;
        this.objetivo = objetivo;
        this.peso = peso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
    
    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
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
        if (!(object instanceof Receta)) {
            return false;
        }
        Receta other = (Receta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Receta[ id=" + id + " ]";
    }
    
}
