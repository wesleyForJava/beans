package com.wesley.bean.pattern.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public abstract class JdbcTemplate {

	DataSource dataSource;

	public JdbcTemplate(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public List<?> excuteQuery(String sql,RowMapper<?> rowMapper,Object [] values){
		
		try {
			//1.建立连接
			Connection conn=this.getConnection();
			//2.创建语句集
			PreparedStatement ps=this.createPrepareStateMent(conn,sql);
			//3.执行语句集
			ResultSet rs = this.createExcutorQuery(ps,values);
			//4.处理结果集
			List<?> result = this.parseResultSet(rs,rowMapper);
			//关闭结果集、语句集、连接
			this.closeConnection(rs,ps,conn);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	protected void closeConnection(ResultSet rs, PreparedStatement preparedStatement, Connection conn) throws SQLException {
		rs.close();
		preparedStatement.close();
		conn.close();
	}


	public List<?> parseResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception {
		int rowNum=1;
		List<Object> result=new ArrayList<Object>();
		while(rs.next()) {
			result.add(rowMapper.rowMap(rs, rowNum++));
		}
		return result;
	}


	protected ResultSet createExcutorQuery(PreparedStatement preparedStatement, Object[] values) throws SQLException {
		for (int i = 0; i < values.length; i++) {
			preparedStatement.setObject(i, values[i]);
		}
		return preparedStatement.executeQuery();
	}


	protected  PreparedStatement createPrepareStateMent(Connection conn, String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}


	protected  Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}
	
	
}
