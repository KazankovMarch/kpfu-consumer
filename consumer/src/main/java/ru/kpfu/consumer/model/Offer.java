package ru.kpfu.consumer.model;

import org.hibernate.annotations.Type;
import ru.kpfu.consumer.model.soap.Content;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Предложение товара/ресурса
 */
@Entity
public class Offer extends Content {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offerIdGenerator")
    @SequenceGenerator(name = "offerIdGenerator", sequenceName = "offer_seq", allocationSize=1)
    Long id;

    @ManyToOne
    private Organization organization;

    @ManyToOne
    private Nomenclature nomenclature;

    private Double priceOfProduct;

    private Float countOfProduct;

    private String unitCode;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    @Temporal(TemporalType.DATE)
    private Date dateOfPerformance;

    @Type(type="pg-uuid")
    private UUID uid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public Double getPriceOfProduct() {
        return priceOfProduct;
    }

    public void setPriceOfProduct(Double priceOfProduct) {
        this.priceOfProduct = priceOfProduct;
    }

    public Float getCountOfProduct() {
        return countOfProduct;
    }

    public void setCountOfProduct(Float countOfProduct) {
        this.countOfProduct = countOfProduct;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
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

    public Date getDateOfPerformance() {
        return dateOfPerformance;
    }

    public void setDateOfPerformance(Date dateOfPerformance) {
        this.dateOfPerformance = dateOfPerformance;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", organization=" + organization +
                ", nomenclature=" + nomenclature +
                ", priceOfProduct=" + priceOfProduct +
                ", countOfProduct=" + countOfProduct +
                ", unitCode='" + unitCode + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", dateOfPerformance=" + dateOfPerformance +
                ", uid=" + uid +
                '}';
    }
}
