package com.ksw.drake.repository;

import com.ksw.drake.dto.MemoDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JDBCMemoRepository implements MemoRepository{
    private final JdbcTemplate jdbcTemplate;

    public JDBCMemoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public JSONObject save(JSONObject req) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(String.valueOf(req));
        JSONObject userRequest = (JSONObject) jsonObject.get("userRequest");
        JSONObject action = (JSONObject) jsonObject.get("action");
        JSONObject params = (JSONObject) action.get("params");

        String title = (String) params.get("title");
        String content = (String) params.get("content");

        JSONObject user = (JSONObject) userRequest.get("user");
        String member_id = (String) user.get("id");
        String memberIdQuery = "Select member_id from \"Member\" where member_id = \'" + member_id + "\'";
        List<ResultSet> list = jdbcTemplate.query(memberIdQuery, (rs, rowNum) -> rs);

        if (list.size() == 0) {
            String query = "Insert into public.\"Member\"(member_id) values (?)";
            jdbcTemplate.update(query, member_id);
        }

        String memoQuery = "INSERT INTO public.\"Memo\"(memo_title, memo_content, member_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(memoQuery, title, content, member_id);

        JSONObject memoObject = new JSONObject();
        memoObject.put("title", title);
        memoObject.put("content", content);
        memoObject.put("memberId", member_id);

        return memoObject;
    }

    public MemoDTO findById(Long id) {
        return null;
    }

    @Override
    public List<MemoDTO> findAll(JSONObject req) throws ParseException {
        JSONParser parser2 = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser2.parse(String.valueOf(req));
        JSONObject userRequest = (JSONObject) jsonObject.get("userRequest");
        JSONObject user = (JSONObject) userRequest.get("user");
        String member_id = (String) user.get("id");
        System.out.println("member_id = " + member_id);

        String memoListQuery = "SELECT memo_title, memo_content from public.\"Memo\" \n" +
                "WHERE member_id = '" + member_id + "'\n" +
                "ORDER BY memo_id DESC\n" +
                "LIMIT 5;";

        List<MemoDTO> memoList = new ArrayList<>();

        jdbcTemplate.query(memoListQuery, (rs, rowNum) -> {
            MemoDTO memo = new MemoDTO(rs.getString("memo_title"), rs.getString("memo_content"));
            memoList.add(memo);

            return rs;
        });

        System.out.println("memoList = " + memoList);

        return memoList;
    }
}
