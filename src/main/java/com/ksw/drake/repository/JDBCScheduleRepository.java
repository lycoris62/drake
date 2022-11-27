package com.ksw.drake.repository;

import com.ksw.drake.dto.ScheduleDTO;
import com.ksw.drake.dto.ScheduleResponseDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JDBCScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public JDBCScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleDTO save(JSONObject req) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(String.valueOf(req));
        JSONObject userRequest = (JSONObject) jsonObject.get("userRequest");
        JSONObject action = (JSONObject) jsonObject.get("action");
        JSONObject params = (JSONObject) action.get("params");
        String scheduleName = (String) params.get("scheduleName");
        String date = (String) params.get("sys_date_time");
        JSONObject dateJSON = (JSONObject) parser.parse(date);

        System.out.println(dateJSON);

        int[] reqDateList = Stream.of(((String) dateJSON.get("date")).split("-")).mapToInt(Integer::parseInt).toArray();
        int[] reqTimeList = Stream.of(((String) dateJSON.get("time")).split(":")).mapToInt(Integer::parseInt).toArray();

        LocalDateTime localDateTime = LocalDateTime.of(reqDateList[0], reqDateList[1], reqDateList[2], reqTimeList[0], reqTimeList[1], reqTimeList[2]);

        JSONObject user = (JSONObject) userRequest.get("user");
        String member_id = (String) user.get("id");
        String memberIdQuery = "Select member_id from \"Member\" where member_id = \'" + member_id + "\'";
        List<ResultSet> list = jdbcTemplate.query(memberIdQuery, (rs, rowNum) -> rs);

        if (list.size() == 0) {
            String query = "Insert into public.\"Member\"(member_id) values (?)";
            jdbcTemplate.update(query, member_id);
        }

        String query2 = "INSERT INTO public.\"Schedule\"(schedule_name, target_date, member_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(query2, scheduleName, localDateTime, member_id);

        String localDateTimeString = localDateTime.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"));
        ScheduleDTO scheduleDTO = new ScheduleDTO(member_id, localDateTime, localDateTimeString, scheduleName);

        return scheduleDTO;
    }

    @Override
    public ScheduleDTO findById(Long id) {
        return null;
    }

    @Override
    public List<ScheduleResponseDTO> findAll(JSONObject req) throws ParseException, SQLException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(String.valueOf(req));
        JSONObject userRequest = (JSONObject) jsonObject.get("userRequest");
        JSONObject user = (JSONObject) userRequest.get("user");
        String member_id = (String) user.get("id");
        System.out.println("member_id = " + member_id);

        String scheduleListQuery = "SELECT schedule_id, schedule_name, target_date, member_id from public.\"Schedule\" \n" +
                "WHERE member_id = '" + member_id + "'\n" +
                "ORDER BY target_date DESC\n" +
                "LIMIT 5;";

        List<ScheduleResponseDTO> scheduleList = new ArrayList<>();

        jdbcTemplate.query(scheduleListQuery, (rs, rowNum) -> {
            System.out.println("rs.getDate(\"target_date\") = " + rs.getDate("target_date"));

            LocalDateTime localDateTime = rs.getDate("target_date").toInstant().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime();
            String localDateTimeString = localDateTime.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"));

            ScheduleResponseDTO schedule = new ScheduleResponseDTO(localDateTimeString, rs.getString("schedule_name"));
            scheduleList.add(schedule);

            return rs;
        });

        System.out.println("scheduleList = " + scheduleList);

        return scheduleList;
    }
}
