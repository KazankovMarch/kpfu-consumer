package ru.kpfu.consumer.rest;

import org.springframework.web.bind.annotation.*;
import ru.kpfu.consumer.model.Request;

import java.util.Date;
import java.util.UUID;

@RestController()
@RequestMapping("/api/request")
public class RequestController {

    @PostMapping
    @ResponseBody
    public Request addRequest(@RequestBody Request request){
        Date now = new Date();
        request.setCreateDate(now);
        request.setModifyDate(now);
        request.setUid(UUID.randomUUID());
        request.setId(System.currentTimeMillis());
        return request;
    }


}
