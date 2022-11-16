package com.ksw.drake.service;

import com.ksw.drake.dto.ScheduleDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

public interface ScheduleService {

    public JSONObject save(JSONObject req) throws ParseException;

    public ScheduleDTO findById(Long id);

    public List<ScheduleDTO> findAll();
}
