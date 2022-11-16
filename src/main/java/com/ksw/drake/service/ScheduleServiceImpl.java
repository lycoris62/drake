package com.ksw.drake.service;

import com.ksw.drake.dto.ScheduleDTO;
import com.ksw.drake.repository.ScheduleRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

public class ScheduleServiceImpl implements ScheduleService{

    private ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public JSONObject save(JSONObject req) throws ParseException {
        return scheduleRepository.save(req);
    }

    @Override
    public ScheduleDTO findById(Long id) {
        return null;
    }

    @Override
    public List<ScheduleDTO> findAll() {
        return null;
    }
}
