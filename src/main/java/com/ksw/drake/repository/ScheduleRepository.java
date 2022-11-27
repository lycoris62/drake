package com.ksw.drake.repository;

import com.ksw.drake.dto.ScheduleDTO;
import com.ksw.drake.dto.ScheduleResponseDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.sql.SQLException;
import java.util.List;

public interface ScheduleRepository {

    public ScheduleDTO save(JSONObject req) throws ParseException;

    public ScheduleDTO findById(Long id);

    public List<ScheduleResponseDTO> findAll(JSONObject req) throws ParseException, SQLException;
}
