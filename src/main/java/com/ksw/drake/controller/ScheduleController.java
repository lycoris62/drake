package com.ksw.drake.controller;

import com.ksw.drake.service.ScheduleService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/api/schedule/create")
    public JSONObject create(@RequestBody JSONObject req) throws ParseException {
        System.out.println("[req]: " + req);
        return scheduleService.save(req);
    }
    @PostMapping("/api/schedule/read")
    public JSONObject read(@RequestBody JSONObject req) throws ParseException {
        System.out.println("[req]: " + req);
        return scheduleService.findAll();
    }
}
