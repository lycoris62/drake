package com.ksw.drake.repository;

import com.ksw.drake.dto.MemoDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

public interface MemoRepository {
    public JSONObject save(JSONObject req) throws ParseException;

    public MemoDTO findById(Long id);

    public List<MemoDTO> findAll();
}
