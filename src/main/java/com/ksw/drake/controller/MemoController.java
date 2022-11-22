package com.ksw.drake.controller;

import com.ksw.drake.service.MemoService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoController {
    private MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping("/api/memo")
    public JSONObject save(@RequestBody JSONObject req) throws ParseException {
        System.out.println("[req]: " + req);
        JSONObject returnedJSON =  memoService.save(req);

        JSONObject jsonObject = new JSONObject();
        JSONArray outputsArray = new JSONArray();
        JSONObject outputsObject = new JSONObject();
        JSONObject output = new JSONObject();
        JSONObject text = new JSONObject();

        jsonObject.put("version", "2.0");
        text.put("text", "===== 메모 추가 완료 =====\n\n[제목]\n" + returnedJSON.get("title") + "\n\n[내용]\n" + returnedJSON.get("content"));
        output.put("simpleText", text);
        outputsArray.add(output);
        outputsObject.put("outputs", outputsArray);

        jsonObject.put("template", outputsObject);

        return jsonObject;
    }
}


