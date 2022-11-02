package com.ksw.drake.controller;

import com.ksw.drake.vo.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EchoController {

    @PostMapping("/api/echo")
    public ResponseMessageVO message(@RequestBody RequestMessageVO vo) {
        ResponseMessageVO resVO = new ResponseMessageVO();
        UserRequestVO userRequest = vo.getUserRequest();
        String utterance = userRequest.getUtterance();

        String str = "echo: " + utterance;

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
}
