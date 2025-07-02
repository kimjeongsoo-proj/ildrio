package ilike.ildrio.common;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.mariadb.jdbc.Driver;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPConnectionManager {

	private BasicDataSource ds = null;

	/**
	 * DBCP를 생성한다.
	 * 
	 * @param dbSvr
	 */
	private void setupDataSource() {

		if (ds == null) {

			ds = new BasicDataSource();

//			String url = "jdbc:mariadb://127.0.0.1:3306/EZWX_PMS";
//			String className = "org.mariadb.jdbc.Driver"; 
//			String userName = "root";
//			String passWord = "kjs656500#";

			String url = "jdbc:mariadb://211.237.0.167:3306/ACEWORK_DB";
			String className = "org.mariadb.jdbc.Driver";
			String userName = "root";
			String passWord = "ildrio11111#db";

			ds.setDriverClassName(className);
			ds.setUrl(url);
			ds.setUsername(userName);
			ds.setPassword(passWord);
			ds.setMaxTotal(100);
			ds.setInitialSize(10);
			ds.setMinIdle(5);
			ds.setMaxWaitMillis(5000);
			ds.setPoolPreparedStatements(true);

		}

	}

	/**
	 * BasicDataSource로부터 connection을 얻어온다.
	 * 
	 * @param dbSvr
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {

		setupDataSource();

		return ds.getConnection();

	}

}
