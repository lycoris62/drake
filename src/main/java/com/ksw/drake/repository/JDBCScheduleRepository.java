package com.ksw.drake.repository;

import com.ksw.drake.dto.ScheduleDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


//DateUtil.java



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
        JSONObject action = (JSONObject) jsonObject.get("action");
        JSONObject params = (JSONObject) action.get("params");
        String scheduleName = (String) params.get("scheduleName");
        String date = (String) params.get("sys_date_time");
        JSONObject dateJSON = (JSONObject) parser.parse(date);
        System.out.println(dateJSON);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = (String) dateJSON.get("date");


        Date targetDate = getDate(
                Integer.parseInt(nowDate.split("-")[0]),
                Integer.parseInt(nowDate.split("-")[1]),
                Integer.parseInt(nowDate.split("-")[2]));

//        Date targetDate = dateFormat.format(nowDate);

//        System.out.println("Date: "+dateFormat.format(nowDate));

        JSONObject user = (JSONObject) userRequest.get("user");
        String member_id = (String) user.get("id");
        String memberIdQuery = "Select member_id from \"Member\" where member_id = \'" + member_id + "\'";
        List<ResultSet> list = jdbcTemplate.query(memberIdQuery, (rs, rowNum) -> rs);

        if (list.size() == 0) {
            String query = "Insert into public.\"Member\"(member_id) values (?)";
            jdbcTemplate.update(query, member_id);
        }
//        String scheduleName = "name";
//        Date nowDate = new Date();
        String query2 = "INSERT INTO public.\"Schedule\"(schedule_name, target_date, member_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(query2, scheduleName, targetDate, member_id);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("scheduleName", scheduleName);
        jsonObject2.put("targetDate", targetDate);
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

    public static Date getDate(int year, int month, int date) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, date);
        return new Date(cal.getTimeInMillis());
    }
}
