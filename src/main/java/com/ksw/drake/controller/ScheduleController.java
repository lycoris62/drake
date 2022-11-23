package com.ksw.drake.controller;

import com.ksw.drake.service.ScheduleService;
import org.json.simple.JSONArray;
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

    @PostMapping("/api/schedule")
    public JSONObject save(@RequestBody JSONObject req) throws ParseException {
        System.out.println("[req]: " + req);
        JSONObject returnedJSON =  scheduleService.save(req);
//
//        JSONObject jsonObject = new JSONObject();
//        JSONArray outputsArray = new JSONArray();
//        JSONObject outputsObject = new JSONObject();
//        JSONObject output = new JSONObject();
//        JSONObject text = new JSONObject();
//
//        jsonObject.put("version", "2.0");
//        text.put("text", "일정: " + returnedJSON.get("targetDate") + "\n내용: " + returnedJSON.get("scheduleName"));
//        output.put("simpleText", text);
//        outputsArray.add(output);
//        outputsObject.put("outputs", outputsArray);
//
//        jsonObject.put("template", outputsObject);

        return returnedJSON;
    }
}
