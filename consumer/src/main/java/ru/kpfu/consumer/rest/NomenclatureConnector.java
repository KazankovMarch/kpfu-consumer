package ru.kpfu.consumer.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.consumer.model.Nomenclature;
import ru.kpfu.consumer.model.NomenclatureList;
import ru.kpfu.consumer.model.soap.Body;
import ru.kpfu.consumer.model.soap.Content;
import ru.kpfu.consumer.model.soap.Envelope;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;

@Service
public class NomenclatureConnector {

    private static final String RESOURCE = "nomenclature";

    @Autowired
    private SoapSender soapSender;


    public Nomenclature addNomenclature(@RequestBody Nomenclature nomenclature){
        Date now = new Date();
        nomenclature.setCreateDate(now);
        nomenclature.setModifyDate(now);
        nomenclature.setUid(UUID.randomUUID());
        nomenclature.setId(System.currentTimeMillis());
        return nomenclature;
    }


    public List<Nomenclature> getNomenclatures(
            Date dateFrom
    ){
//        Nomenclature n1 = addNomenclature(
//                new Nomenclature(
//                    "PECHENKA",
//                        null,
//                        null
//                )
//        );
//        Nomenclature n2 = addNomenclature(
//                new Nomenclature(
//                        "PONCHICK",
//                        null,
//                        null
//                )
//        );
//        List<Nomenclature> list = new ArrayList<>();
//        list.add(n1);
//        list.add(n2);
        try {
            Envelope envelope = soapSender.getAllRequest(RESOURCE, dateFrom);
            Body body = envelope.getBody();
            NomenclatureList nomenclatureList = (NomenclatureList) body.getContent();
            List<Nomenclature> list = nomenclatureList.getNomenclatureList();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
