package com.ksw.drake.service;

import com.ksw.drake.dto.MemoDTO;
import com.ksw.drake.dto.ScheduleDTO;
import com.ksw.drake.repository.MemoRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

public class MemoServiceImpl implements MemoService {
    private MemoRepository memoRepository;

    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public JSONObject save(JSONObject req) throws ParseException {
        return memoRepository.save(req);
    }

    @Override
    public MemoDTO findById(Long id) {
        return null;
    }

    @Override
    public List<MemoDTO> findAll() {
        return null;
    }
}
