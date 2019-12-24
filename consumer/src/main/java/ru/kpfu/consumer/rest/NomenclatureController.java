package ru.kpfu.consumer.rest;


import org.springframework.web.bind.annotation.*;
import ru.kpfu.consumer.model.Nomenclature;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/nomenclature")
public class NomenclatureController {


    @PostMapping
    @ResponseBody
    public Nomenclature addNomenclature(@RequestBody Nomenclature nomenclature){
        Date now = new Date();
        nomenclature.setCreateDate(now);
        nomenclature.setModifyDate(now);
        nomenclature.setUid(UUID.randomUUID());
        nomenclature.setId(System.currentTimeMillis());
        return nomenclature;
    }


    @GetMapping
    @ResponseBody
    public List<Nomenclature> getNomenclatures(
            @RequestParam Date dateFrom,
            @RequestParam(defaultValue = "30") int limit
    ){
        Nomenclature n1 = addNomenclature(
                new Nomenclature(
                    "PECHENKA",
                        null,
                        null
                )
        );
        Nomenclature n2 = addNomenclature(
                new Nomenclature(
                        "PONCHICK",
                        null,
                        null
                )
        );
        List<Nomenclature> list = new ArrayList<>();
        list.add(n1);
        list.add(n2);
        return list;
    }

}
