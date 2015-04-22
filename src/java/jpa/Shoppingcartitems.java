/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author raynak
 */
@Entity
@Table(name = "SHOPPINGCARTITEMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shoppingcartitems.findAll", query = "SELECT s FROM Shoppingcartitems s"),
    @NamedQuery(name = "Shoppingcartitems.findById", query = "SELECT s FROM Shoppingcartitems s WHERE s.id = :id")})
public class Shoppingcartitems implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne
    private Users userId;
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    @ManyToOne
    private Books itemId;

    public Shoppingcartitems() {
    }

    public Shoppingcartitems(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Books getItemId() {
        return itemId;
    }

    public void setItemId(Books itemId) {
        this.itemId = itemId;
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
        if (!(object instanceof Shoppingcartitems)) {
            return false;
        }
        Shoppingcartitems other = (Shoppingcartitems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Shoppingcartitems[ id=" + id + " ]";
    }
    
}
