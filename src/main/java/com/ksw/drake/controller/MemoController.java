package com.ksw.drake.controller;

import com.ksw.drake.service.MemoService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
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
        return memoService.save(req);
    }
}


