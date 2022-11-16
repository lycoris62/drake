package com.ksw.drake.repository;

import com.ksw.drake.dto.ScheduleDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public class JDBCScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public JDBCScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public JSONObject save(JSONObject req) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(String.valueOf(req));
        JSONObject userRequest = (JSONObject) jsonObject.get("userRequest");
        JSONObject user = (JSONObject) userRequest.get("user");
        String member_id = (String) user.get("id");
        String memberIdQuery = "Select member_id from \"Member\" where member_id = \'" + member_id + "\'";
        List<ResultSet> list = jdbcTemplate.query(memberIdQuery, (rs, rowNum) -> rs);

        if (list.size() == 0) {
            String query = "Insert into public.\"Member\"(member_id) values (?)";
            jdbcTemplate.update(query, member_id);
        }
        String scheduleName = "name";
        Date nowDate = new Date();
        String query2 = "INSERT INTO public.\"Schedule\"(schedule_name, target_date, member_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(query2, scheduleName, nowDate, member_id);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("scheduleName", scheduleName);
        jsonObject2.put("targetDate", nowDate);
        jsonObject2.put("memberId", member_id);

        return jsonObject2;
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
