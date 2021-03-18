package com.webapp.TextBook;

import com.webapp.TextBook.Model.SCBCRSEModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class TextBookApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		System.out.println("I work!!!");
		SpringApplication.run(TextBookApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("I am now in the database part!");
		String sql = "SELECT * FROM SCBCRSE";

		List<SCBCRSEModel> scbcrseList = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(SCBCRSEModel.class));

		scbcrseList.forEach(System.out :: println);
		String sqlChanging = "UPDATE SCBCRSE;" +
				"SET SCBCRSE_CRSE_NUMB = 'haha'";

		scbcrseList = jdbcTemplate.query(sqlChanging,
				BeanPropertyRowMapper.newInstance(SCBCRSEModel.class));

		scbcrseList.forEach(System.out :: println);
	}
}
