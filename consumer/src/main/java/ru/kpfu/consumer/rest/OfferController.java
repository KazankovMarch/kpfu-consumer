package ru.kpfu.consumer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.consumer.model.Offer;
import ru.kpfu.consumer.model.Nomenclature;
import ru.kpfu.consumer.model.Organization;

import java.util.*;

@RestController
@RequestMapping("/api/offer")
public class OfferController {

    @Autowired NomenclatureController nomenclatureController;

    @GetMapping
    @ResponseBody
    public List<Offer> getOffers(@RequestParam Long nomenclatureId){
        Date now = new Date();

        Offer offer = new Offer();
        offer.setNomenclature(
                nomenclatureController.addNomenclature(
                        new Nomenclature("BUBLIC", now, now)
                )
        );
        offer.setUid(UUID.randomUUID());
        offer.setModifyDate(now);
        offer.setCreateDate(now);
        Organization organization = new Organization();
        organization.setAdressOfOrganization("Pushkina odm kolotushkina");
        organization.setId(System.currentTimeMillis());
        organization.setKpp("_KPP_");
        offer.setOrganization(
                organization
        );
        return Collections.singletonList(offer);
    }


}
