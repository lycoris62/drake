package com.ksw.drake.controller;

import com.ksw.drake.vo.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EchoController {

//    vo 디렉토리랑 이 매핑은 밑의 jsonobject로 대체할 예정입니다 그에 따라 vo들 다 사라질 예정이니 놀라지 마세요
    @PostMapping("/api/echo")
    public ResponseMessageVO message(@RequestBody RequestMessageVO vo) {
        ResponseMessageVO resVO = new ResponseMessageVO();
        UserRequestVO userRequest = vo.getUserRequest();
        String utterance = userRequest.getUtterance();
        DataVO data = new DataVO();

        String str = "echo: " + utterance;

        data.setMsg(str);
        resVO.setData(data);

        SimpleTextVO simpleText = new SimpleTextVO();
        simpleText.setText(str);

        OutputsVO outputs = new OutputsVO();
        outputs.setSimpleText(simpleText);
        List<OutputsVO> outputsList = new ArrayList<>();
        outputsList.add(outputs);

        TemplateVO template = new TemplateVO();
        template.setOutputs(outputsList);

        resVO.setVersion("2.0");
        resVO.setTemplate(template);

        return resVO;
    }

//    쉬운 JSON 예제를 만들어뒀습니다. 이거보며 연습해보세요
    @GetMapping("/jsontest")
    public JSONObject jsonTest(@RequestBody JSONObject test) {
        JSONObject jsonObject = new JSONObject();
        JSONArray outputsArray = new JSONArray();
        JSONObject outputsObject = new JSONObject();
        JSONObject output = new JSONObject();
        JSONObject text = new JSONObject();

        jsonObject.put("version", "2.0");
        text.put("text", "echo: " + test.get("tt"));
        output.put("simpleText", text);
        outputsArray.add(output);
        outputsObject.put("outputs", outputsArray);

        jsonObject.put("template", outputsObject);

        return jsonObject;
    }
}
