package ru.kpfu.consumer.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.kpfu.consumer.model.soap.Body;
import ru.kpfu.consumer.model.soap.Content;
import ru.kpfu.consumer.model.soap.Envelope;
import ru.kpfu.consumer.model.soap.Header;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

@Component
@PropertySource("classpath:custom.properties")
public final class SoapSender {
    private final JAXBContext context;
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;
    private final String centralServerHost;

    public final JAXBContext getContext() {
        return this.context;
    }

    public final Marshaller getMarshaller() {
        return this.marshaller;
    }

    public final Unmarshaller getUnmarshaller() {
        return this.unmarshaller;
    }

    public final Envelope getAllRequest(String resource, Date dateFrom) throws IOException, JAXBException {
        HttpURLConnection connection = this.getConnection(resource, "get", dateFrom);
        connection.setRequestMethod("POST");
        Object var10000 = this.unmarshaller.unmarshal(connection.getInputStream());
        connection.disconnect();
        return (Envelope)var10000;
    }

    
    public final Envelope postRequest(Content content, String resource, String method) throws IOException, JAXBException {
        Header header = new Header();
        Body body = new Body();
        body.setContent(content);
        Envelope envelope = new Envelope();
        envelope.setHeader(header);
        envelope.setBody(body);

        HttpURLConnection connection = this.getConnection(resource, method, null);
        connection.setRequestMethod("POST");
        this.marshaller.marshal(envelope, connection.getOutputStream());
        Envelope var10000 = (Envelope) this.unmarshaller.unmarshal(connection.getInputStream());
        connection.disconnect();
        return var10000;
    }

    private final HttpURLConnection getConnection(String resource, String method, Date dateFrom) throws IOException {
        String stringUrl = "http://" + this.centralServerHost + '/' + resource + '/' + method;
        if(dateFrom!=null){
            stringUrl += "?datefrom=" + URLEncoder.encode(MyDateFormat.format.format(dateFrom), "UTF-8");
        }
        URL url = new URL(stringUrl);
        System.out.println(url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/xml");
        connection.setDoOutput(true);
        return connection;
    }

    public SoapSender(@Value("${central.server.host}")  String centralServerHost) throws JAXBException {
        this.centralServerHost = centralServerHost;
        this.context = JAXBContext.newInstance(Envelope.class);
        this.marshaller = this.context.createMarshaller();
        this.unmarshaller = this.context.createUnmarshaller();
        this.marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
    }
}
