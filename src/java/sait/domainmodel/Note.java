/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sait.domainmodel;

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
 * @author 733196
 */
@Entity
@Table(name = "note")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n")
    , @NamedQuery(name = "Note.findByNoteid", query = "SELECT n FROM Note n WHERE n.noteid = :noteid")
    , @NamedQuery(name = "Note.findByDateCreated", query = "SELECT n FROM Note n WHERE n.dateCreated = :dateCreated")
    , @NamedQuery(name = "Note.findByContent", query = "SELECT n FROM Note n WHERE n.content = :content")})
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "noteid")
    private Integer noteid;
    @Basic(optional = false)
    @Column(name = "dateCreated")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "content")
    private String content;

    public Note() {
    }

    public Note(Integer noteid) {
        this.noteid = noteid;
    }

    public Note(Integer noteid, Date dateCreated, String content) {
        this.noteid = noteid;
        this.dateCreated = dateCreated;
        this.content = content;
    }
    
    public Note(Date dateCreated,String content){
        this.dateCreated = dateCreated;
        this.content = content;
    }

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noteid != null ? noteid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.noteid == null && other.noteid != null) || (this.noteid != null && !this.noteid.equals(other.noteid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sait.domainmodel.Note[ noteid=" + noteid + " ]";
    }
    
}
