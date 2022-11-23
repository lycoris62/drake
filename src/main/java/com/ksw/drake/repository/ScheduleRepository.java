package com.ksw.drake.repository;

import com.ksw.drake.dto.ScheduleDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

public interface ScheduleRepository {

    public ScheduleDTO save(JSONObject req) throws ParseException;

    public ScheduleDTO findById(Long id);

    public List<ScheduleDTO> findAll();
}
