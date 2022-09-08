package org.example.dao;

import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.
                query("SELECT * FROM person",
                        new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(final int id) {
        return jdbcTemplate.
                query("SELECT * FROM person WHERE id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, age, email) VALUES(?, ?, ?)",
                person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id=?",
                person.getName(), person.getAge(), person.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    public void testMultipleUpdate() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            jdbcTemplate.update("INSERT INTO person(name, age, email) VALUES (?, ?, ?)",
                    "Tom" + i, 30, "hardy" + i + "mail.com");
        }
        System.out.println("time: " + (System.currentTimeMillis() - start));
    }

    public void testBatchUpdate() {
        long start = System.currentTimeMillis();
        jdbcTemplate.batchUpdate("INSERT INTO person(name, age, email) VALUES (?, ?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, "Ratmir" + i);
                ps.setInt(2, 32);
                ps.setString(3, "ratmir" + i + "mail.ru");
            }

            @Override
            public int getBatchSize() {
                return 1000;
            }
        });
        System.out.println("time batch: " + (System.currentTimeMillis() - start));
    }
}