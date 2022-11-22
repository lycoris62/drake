package com.ksw.drake.service;

//import com.ksw.drake.dto.ScheduleDTO;
import com.ksw.drake.dto.MemoDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

public interface MemoService {
    public JSONObject save(JSONObject req) throws ParseException;

    public MemoDTO findById(Long id);

    public List<MemoDTO> findAll();
}
