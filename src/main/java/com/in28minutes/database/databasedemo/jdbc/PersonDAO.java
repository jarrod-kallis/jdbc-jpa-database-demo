package com.in28minutes.database.databasedemo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.in28minutes.database.databasedemo.entity.Person;

@Repository
public class PersonDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	class PersonRowMapper implements RowMapper<Person> {

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setDob(rs.getTimestamp("dob"));
			
			return person;
		}
		
	}

	public List<Person> findAll() {
		// We can use a BeanPropertyRowMapper to map the DB results directly to
		// the Person class as long as the columns & field names match exactly.
		// Person Table "BIRTH_DATE" = Person Class "birthDate"
		// return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<Person>(Person.class));
		
		return jdbcTemplate.query("select * from person", new PersonRowMapper());
	}

	public Person findById(int id) {
		return jdbcTemplate.queryForObject("select * from person where id = ?", new Object[] { id },
				new BeanPropertyRowMapper<Person>(Person.class));
	}

	public void deleteById(int id) {
		jdbcTemplate.update("delete from person where id = ?", new Object[] { id });
	}

	public void deleteByMultipleIds(List<Integer> ids) {
		namedJdbcTemplate.update("delete from person where id in (:ids)", Collections.singletonMap("ids", ids));
	}

	public void insert(Person person) {
		jdbcTemplate.update("insert into person (id, name, location, dob) values (?,?,?,?)",
				new Object[] { person.getId(), person.getName(), person.getLocation(), person.getDob() });
	}

	public void update(Person person) {
		jdbcTemplate.update("update person set name = ?, location = ? where id = ?",
				new Object[] { person.getName(), person.getLocation(), person.getId() });
	}
}
