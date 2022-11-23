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
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleServiceImpl implements ScheduleService{

    @Value("${elasticsearch.url}")
    private String ELASTIC_URL;
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

        ScheduleDTO scheduleDTO = new ScheduleDTO(
                (String) returnedJSON.get("memberId"),
                (LocalDateTime) returnedJSON.get("localDataTime"),
                (String) returnedJSON.get("scheduleName"));
        sendScheduleToElastic(scheduleDTO);

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
