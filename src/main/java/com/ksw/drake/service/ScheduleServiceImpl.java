package com.ksw.drake.service;

import com.ksw.drake.dto.ScheduleDTO;
import com.ksw.drake.dto.ScheduleResponseDTO;
import com.ksw.drake.repository.ScheduleRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

public class ScheduleServiceImpl implements ScheduleService{

    @Value("${elasticsearch.url}")
    private String ELASTIC_URL;
    private ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    JSONObject jsonObject = new JSONObject();
    JSONArray outputsArray = new JSONArray();
    JSONObject outputsObject = new JSONObject();
    JSONObject output = new JSONObject();
    JSONObject text = new JSONObject();

    @Override
    public JSONObject save(JSONObject req) throws ParseException {
        ScheduleDTO scheduleDTO = scheduleRepository.save(req);

        jsonObject.put("version", "2.0");
        text.put("text", "일정: " + scheduleDTO.getTargetDate() + "\n내용: " + scheduleDTO.getScheduleName());
        output.put("simpleText", text);
        outputsArray.add(output);
        outputsObject.put("outputs", outputsArray);

        jsonObject.put("template", outputsObject);

        sendScheduleToElastic(scheduleDTO);

        return jsonObject;
    }

    @Override
    public ScheduleDTO findById(Long id) {
        return null;
    }

    @Override
    public JSONObject findAll(JSONObject req) throws SQLException, ParseException {
        List<ScheduleResponseDTO> scheduleList = scheduleRepository.findAll(req);
        JSONObject listCard = new JSONObject();
        JSONObject header = new JSONObject();
        JSONObject headerTitle = new JSONObject();
        JSONArray itemsArray = new JSONArray();

        headerTitle.put("title", "일정 목록");
        header.put("header", headerTitle);
        listCard.put("listCard", header);

        for (ScheduleResponseDTO schedule : scheduleList) {
            JSONObject item = new JSONObject();
            item.put(schedule.getTargetDate(), schedule.getScheduleName());
            itemsArray.add(item);
        }
        header.put("items", itemsArray);

        jsonObject.put("version", "2.0");
        output.put("simpleText", text);

        outputsArray.add(listCard);
        outputsObject.put("outputs", outputsArray);

        jsonObject.put("template", outputsObject);
        System.out.println("jsonObject = " + jsonObject);

        return jsonObject;
    }

    public void sendScheduleToElastic(ScheduleDTO scheduleDTO) {
        URI uri = UriComponentsBuilder
                .fromUriString(ELASTIC_URL)
                .path("/elastic")
                .encode()
                .build()
                .toUri();

        System.out.println("uri = " + uri + ", data = " + scheduleDTO.toString());

        RequestEntity<ScheduleDTO> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(scheduleDTO);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
        System.out.println("response = " + response);
    }
}
