package com.ksw.drake.service;

import com.ksw.drake.dto.ScheduleDTO;
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
    public JSONObject findAll() {
        JSONObject listCard = new JSONObject();
        JSONObject header = new JSONObject();
        JSONObject headerTitle = new JSONObject();
        JSONArray itemsArray = new JSONArray();
        JSONObject item1 = new JSONObject();
        JSONObject item2 = new JSONObject();
        JSONObject item3 = new JSONObject();
        JSONObject item4 = new JSONObject();
        JSONObject item5 = new JSONObject();

        headerTitle.put("title", "일정 목록");
        header.put("header", headerTitle);
        listCard.put("listCard", header);
        header.put("items", itemsArray);
        item1.put("title", "title1");
        item1.put("description", "desc1");
        item2.put("title", "title2");
        item2.put("description", "desc2");
        item3.put("title", "title3");
        item3.put("description", "desc3");
        item4.put("title", "title4");
        item4.put("description", "desc4");
        item5.put("title", "title5");
        item5.put("description", "desc5");
        itemsArray.add(item1);
        itemsArray.add(item2);
        itemsArray.add(item3);
        itemsArray.add(item4);
        itemsArray.add(item5);

        jsonObject.put("version", "2.0");
        output.put("simpleText", text);

        outputsArray.add(listCard);
        outputsObject.put("outputs", outputsArray);

        jsonObject.put("template", outputsObject);

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
