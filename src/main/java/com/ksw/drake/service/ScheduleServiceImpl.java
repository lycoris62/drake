package com.ksw.drake.service;

import com.ksw.drake.dto.ScheduleDTO;
import com.ksw.drake.repository.ScheduleRepository;
import org.json.simple.JSONArray;
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
        JSONObject returnedJSON = scheduleRepository.save(req);

        JSONObject jsonObject = new JSONObject();
        JSONArray outputsArray = new JSONArray();
        JSONObject outputsObject = new JSONObject();
        JSONObject output = new JSONObject();
        JSONObject text = new JSONObject();

        jsonObject.put("version", "2.0");
        text.put("text", "일정: " + returnedJSON.get("targetDate") + "\n내용: " + returnedJSON.get("scheduleName"));
        output.put("simpleText", text);
        outputsArray.add(output);
        outputsObject.put("outputs", outputsArray);

        jsonObject.put("template", outputsObject);

        return jsonObject;
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
