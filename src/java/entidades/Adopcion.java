/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "adopcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adopcion.findAll", query = "SELECT a FROM Adopcion a"),
    @NamedQuery(name = "Adopcion.findById", query = "SELECT a FROM Adopcion a WHERE a.id = :id"),
    @NamedQuery(name = "Adopcion.findByClienteId", query = "SELECT a FROM Adopcion a WHERE a.clienteId = :clienteId"),
    @NamedQuery(name = "Adopcion.findByMascotaId", query = "SELECT a FROM Adopcion a WHERE a.mascotaId = :mascotaId"),
    @NamedQuery(name = "Adopcion.findByFecha", query = "SELECT a FROM Adopcion a WHERE a.fecha = :fecha")})
public class Adopcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "cliente_id")
    private int clienteId;
    @Basic(optional = false)
    @Column(name = "mascota_id")
    private int mascotaId;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Adopcion() {
    }

    public Adopcion(Integer id) {
        this.id = id;
    }

    public Adopcion(Integer id, int clienteId, int mascotaId, Date fecha) {
        this.id = id;
        this.clienteId = clienteId;
        this.mascotaId = mascotaId;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(int mascotaId) {
        this.mascotaId = mascotaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
        if (!(object instanceof Adopcion)) {
            return false;
        }
        Adopcion other = (Adopcion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Adopcion[ id=" + id + " ]";
    }
    
}
