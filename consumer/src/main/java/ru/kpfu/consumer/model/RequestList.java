package ru.kpfu.consumer.model;

import ru.kpfu.consumer.model.soap.Content;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class RequestList extends Content {

    private List<Request> requestList;

    public RequestList(List<Request> requests) {
        requestList = requests;
    }

    public RequestList() {
    }

    @XmlElement(name = "request")
    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }
}
