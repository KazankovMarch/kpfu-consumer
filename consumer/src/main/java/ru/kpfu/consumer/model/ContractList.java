package ru.kpfu.consumer.model;

import ru.kpfu.consumer.model.soap.Content;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class ContractList extends Content {

    private List<Contract> contractList;

    public ContractList(List<Contract> contracts) {
        contractList = contracts;
    }

    public ContractList() {
    }

    @XmlElement(name = "contract")
    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }
}
