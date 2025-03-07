package ilike.ildrio.common;

import org.springframework.beans.factory.annotation.Autowired;
import java.net.InetAddress;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

import ilike.ildrio.common.DBCPConnectionManager;
import ilike.ildrio.common.StringUtil;

public class DBCPTransaction {

	public static void main(String[] args) throws Exception {

		String timeStamp = StringUtil.getNow("timeStamp");
		

	}

	public static int dbcbTrxnOne(String sql) throws Exception {
		int iRtn = 0;
		/** connection **/
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			DBCPConnectionManager cm = new DBCPConnectionManager();
			conn = cm.getConnection();
			stmt = conn.createStatement();

			//System.out.println(sql);
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
			iRtn = 1;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		
		return iRtn;
	}

	public static int dbcbTrxnBatch(ArrayList<String> arrSql, String displayYn) throws Exception {
		
		int iRtn = 0;
		
		/** connection **/
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			DBCPConnectionManager cm = new DBCPConnectionManager();
			conn = cm.getConnection();
			stmt = conn.createStatement();

			if (arrSql.size() > 0) {
				for (String query : arrSql) {
					stmt.addBatch(query);
					if(displayYn.equals("Y")) {
						System.out.println(query);
					}
				}
				stmt.executeBatch();
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			//System.out.println("배치에러 >>>>>>>>>>>>>>>");
			iRtn = 1;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return iRtn;
	}

	public static Map<String, String> dbcpSelectMap(String sql) throws Exception {

		Map<String, String> rtnMap = new HashMap<String, String>();

		/** connection **/
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			DBCPConnectionManager cm = new DBCPConnectionManager();
			conn = cm.getConnection();
			stmt = conn.createStatement();

			//System.out.println(sql);
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				ResultSetMetaData rsMD = rs.getMetaData();
				int rsMDCnt = rsMD.getColumnCount();
				for (int i = 1; i <= rsMDCnt; i++) {
					String column = rsMD.getColumnName(i);
					String value = StringUtil.NVL(rs.getString(column));

					rtnMap.put(column, value);
				}

			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return rtnMap;
	}

	public static ArrayList<HashMap<String, String>> dbcpSelectList(String sql) throws Exception {

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		/** connection **/
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			DBCPConnectionManager cm = new DBCPConnectionManager();
			conn = cm.getConnection();
			stmt = conn.createStatement();
			
			//System.out.println(sql);
			rs = stmt.executeQuery(sql);
			
			int irow = 0;
			while (rs.next()) {

				HashMap<String, String> rtnMap = new HashMap<String, String>();

				ResultSetMetaData rsMD = rs.getMetaData();
				int rsMDCnt = rsMD.getColumnCount();
				for (int i = 1; i <= rsMDCnt; i++) {
					String column = rsMD.getColumnName(i);
					String value = StringUtil.NVL(rs.getString(column));

					rtnMap.put(column, value);
				}

				list.add(rtnMap);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return list;
	}
	
}