package ru.kpfu.consumer.model;

import ru.kpfu.consumer.model.soap.Content;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class NomenclatureList extends Content {

    private List<Nomenclature> nomenclatureList;

    public NomenclatureList(List<Nomenclature> nomenclatures) {
        nomenclatureList = nomenclatures;
    }

    public NomenclatureList() {
    }

    @XmlElement(name = "nomenclature")
    public List<Nomenclature> getNomenclatureList() {
        return nomenclatureList;
    }

    public void setNomenclatureList(List<Nomenclature> nomenclatureList) {
        this.nomenclatureList = nomenclatureList;
    }
}
