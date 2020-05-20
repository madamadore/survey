package it.matteoavanzini.survey.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import it.matteoavanzini.survey.model.Question;

public class QuestionRepositoryImpl implements JdbcQuestionRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from question", Integer.class);
    }

    @Override
    public int save(Question question) {
        return jdbcTemplate.update("insert into question (title, description, multiple) values(?,?,?)",
                question.getTitle(), question.getDescription(), question.isMultiple());
    }

    @Override
    public int update(Question question) {
        return jdbcTemplate.update("update question set title = ?, description = ?, multiple = ? where id = ?",
                question.getTitle(), question.getDescription(), question.isMultiple(), question.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("delete question where id = ?", id);
    }

    @Override
    public List<Question> findAll() {
        return jdbcTemplate.query("select * from question", new RowMapper<Question>() {
            public Question mapRow(ResultSet rs, int rowNum) {
                    Question q = new Question();
                    try {
                        q.setId(rs.getLong("id"));
                        q.setTitle(rs.getString("title"));
                        q.setDescription(rs.getString("description"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return q;
                }
            });
    }

    @Override
    public Optional<Question> findById(Long id) {
        return jdbcTemplate.queryForObject(
            "select * from books where id = ?",
            new Object[]{id},
            (rs, rowNum) -> {
                Question q = new Question();
                q.setId(rs.getLong("id"));
                q.setTitle(rs.getString("title"));
                q.setDescription(rs.getString("description"));
                return Optional.of(q);
            });
    }

}