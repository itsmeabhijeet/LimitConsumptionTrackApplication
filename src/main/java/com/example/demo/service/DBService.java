/**
 * 
 */
package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.example.demo.models.Transaction;

/**
 * @author Abhijeet Gupta
 *
 */
@Service
public class DBService {

	@Value("${app.datasource.driverClassName}")
	private String driverClassName;
	@Value("${app.datasource.url}")
	private String url;
	@Value("${app.datasource.username}")
	private String username;
	@Value("${app.datasource.password}")
	private String password;

	private JdbcTemplate jdbcTemplateObject;

	/*
	 * @Autowired LimitRepository limitRepository;
	 */

	@Bean
	public DBService dbService() {
		DBService dbService = new DBService();
		System.out.println("*******" + driverClassName + url + username + password);
//		DataSource dataSource = DataSourceBuilder.create().username(username).password(password).url(url)
//				.driverClassName(driverClassName).build();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
		return dbService;
	}

	// public DataSource getDataSource() {
	// DataSource dataSource =
	// DataSourceBuilder.create().username(username).password(password).url(url)
	// .driverClassName(driverClassName).build();
	// this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	// return dataSource;
	// }

	public synchronized double fetchLimit(Transaction transaction) {
		return 0;
	}

	
	public int update(Integer id, String name) {
		String SQL = "update accountnumber set name = ? where id = ?";
		int update = jdbcTemplateObject.update(SQL, name, id);
		System.out.println("Updated Record with ID = " + id + " " + update);
		return update;
	}

}
