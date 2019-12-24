package ru.kpfu.consumer.model;

import ru.kpfu.consumer.model.soap.Content;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class OfferList extends Content {

    private List<Offer> offerList;

    public OfferList(List<Offer> nomenclatures) {
        offerList = nomenclatures;
    }

    public OfferList() {
    }

    @XmlElement(name = "offer")
    public List<Offer> getOfferList() {
        return offerList;
    }

    public void setOfferList(List<Offer> offerList) {
        this.offerList = offerList;
    }
}
