/**
 * 
 */
package com.example.demo.service;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

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

	@Bean
	public DataSource dbService() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
		return dataSource;
	}

	@Transactional
	public int updateLimitValues(Transaction transaction) {
		int res = 0;
		String fetchLimitQuery = "select amount from limitdefinition"
				+ " where accountnumber_id =(select id from accountnumber where name ='"
				+ transaction.getAccountNumber() + "'" + ")"
				+ " and transactiontype_id= (select id from transactiontype where name='" + transaction.getType() + "'"
				+ ")" + "and frequency_id = (select id from frequency where type ='Daily')";

		String updateLimitQuery = "update limitdefinition set amount = ?"
				+ " where accountnumber_id =(select id from accountnumber where name ='"
				+ transaction.getAccountNumber() + "'" + ")"
				+ " and transactiontype_id= (select id from transactiontype where name='" + transaction.getType() + "'"
				+ ")" + "and frequency_id = (select id from frequency where type ='Daily')";

		double limit = jdbcTemplateObject.queryForObject(fetchLimitQuery, Double.class);
		if (limit > transaction.getTransactionAmount()) {
			double updatedLimit = limit - transaction.getTransactionAmount();
			res = jdbcTemplateObject.update(updateLimitQuery,updatedLimit);
		} else
			return -1;

		return res;
	}

}
