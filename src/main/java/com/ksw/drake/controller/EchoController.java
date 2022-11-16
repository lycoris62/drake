package com.ksw.drake.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

@RestController
public class EchoController {

    @PostMapping("/api/echo")
    public JSONObject message(@RequestBody String req) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONObject reqJSON = (JSONObject) parser.parse(req);
        JSONObject UserRequest = (JSONObject) reqJSON.get("userRequest");

        JSONObject jsonObject = new JSONObject();
        JSONArray outputsArray = new JSONArray();
        JSONObject outputsObject = new JSONObject();
        JSONObject output = new JSONObject();
        JSONObject text = new JSONObject();

        jsonObject.put("version", "2.0");
        text.put("text", "echo: " + UserRequest.get("utterance"));
        output.put("simpleText", text);
        outputsArray.add(output);
        outputsObject.put("outputs", outputsArray);

        jsonObject.put("template", outputsObject);

        return jsonObject;
    }

//    쉬운 JSON 예제를 만들어뒀습니다. 이거보며 연습해보세요
    @GetMapping("/jsontest")
    public JSONObject jsonTest(@RequestBody JSONObject test) {
        JSONObject jsonObject = new JSONObject();
        JSONArray outputsArray = new JSONArray();
        JSONObject outputsObject = new JSONObject();
        JSONObject output = new JSONObject();
        JSONObject text = new JSONObject();

        jsonObject.put("version", "2.0");
        text.put("text", "echo: " + test.get("tt"));
        output.put("simpleText", text);
        outputsArray.add(output);
        outputsObject.put("outputs", outputsArray);

        jsonObject.put("template", outputsObject);

        return jsonObject;
    }
}
