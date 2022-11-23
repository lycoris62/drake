package com.ksw.drake.service;

import com.ksw.drake.dto.MemoDTO;
import com.ksw.drake.repository.MemoRepository;
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
import java.util.List;

public class MemoServiceImpl implements MemoService {
    @Value("${elasticsearch.url}")
    private String ELASTIC_URL;
    private MemoRepository memoRepository;

    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public JSONObject save(JSONObject req) throws ParseException {
        JSONObject returnedJSON = memoRepository.save(req);

        JSONObject jsonObject = new JSONObject();
        JSONArray outputsArray = new JSONArray();
        JSONObject outputsObject = new JSONObject();
        JSONObject output = new JSONObject();
        JSONObject text = new JSONObject();

        jsonObject.put("version", "2.0");
        text.put("text", "===== 메모 추가 완료 =====\n\n[제목]\n" + returnedJSON.get("title") + "\n\n[내용]\n" + returnedJSON.get("content"));
        output.put("simpleText", text);
        outputsArray.add(output);
        outputsObject.put("outputs", outputsArray);

        jsonObject.put("template", outputsObject);

        MemoDTO memoDTO = new MemoDTO((String) returnedJSON.get("memberId"), (String) returnedJSON.get("title"), (String) returnedJSON.get("content"));
        sendMemoToElastic(memoDTO);

        return jsonObject;
    }

    @Override
    public MemoDTO findById(Long id) {
        return null;
    }

    @Override
    public List<MemoDTO> findAll() {
        return null;
    }

    public void sendMemoToElastic(MemoDTO memoDTO) {
        URI uri = UriComponentsBuilder
                .fromUriString(ELASTIC_URL)
                .path("/elastic")
                .encode()
                .build()
                .toUri();

        System.out.println("uri = " + uri + ", data = " + memoDTO.toString());

        RequestEntity<MemoDTO> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(memoDTO);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
        System.out.println("response = " + response);
    }
}
