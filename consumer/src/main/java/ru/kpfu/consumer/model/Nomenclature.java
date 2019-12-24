package ru.kpfu.consumer.model;

import org.hibernate.annotations.Type;
import ru.kpfu.consumer.model.soap.Content;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Nomenclature extends Content {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nomenclatureIdGenerator")
    @SequenceGenerator(name = "nomenclatureIdGenerator", sequenceName = "nomenclature_seq", allocationSize=1)
    Long id;

    @Type(type="pg-uuid")
    private UUID uid;

    private String productName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    public Nomenclature() {
    }

    public Nomenclature(String productName, Date createDate, Date modifyDate) {
        this.productName = productName;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public UUID getUid() {
        if (uid == null) {
             uid = UUID.randomUUID();
        }
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Nomenclature {" +
                "productName='" + productName + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
