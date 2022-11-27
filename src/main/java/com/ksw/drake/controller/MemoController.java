package com.ksw.drake.controller;

import com.ksw.drake.service.MemoService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class MemoController {
    private MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping("/api/memo/create")
    public JSONObject save(@RequestBody JSONObject req) throws ParseException {
        System.out.println("[req]: " + req);
        return memoService.save(req);
    }
    @PostMapping("/api/memo/read")
    public JSONObject read(@RequestBody JSONObject req) throws ParseException, SQLException {
        System.out.println("[req]: " + req);
        return memoService.findAll(req);
    }
}


