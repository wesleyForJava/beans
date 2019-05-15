package com.wesley.bean.pattern.template;

import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

public class MemberDao extends JdbcTemplate{

	public MemberDao(DataSource dataSource) {
		super(dataSource);
	}
	
	public List<?> selectAll(){
		
		String sql="select * from t_member";
		
		return super.excuteQuery(sql, new RowMapper<Member>(){

			@Override
			public Member rowMap(ResultSet rs, int rowNum) throws Exception {
				Member mem=new Member();
				mem.setUsername(rs.getString("username"));
				mem.setPassword(rs.getString("password"));
				mem.setAge(rs.getInt("age"));
				mem.setAddr(rs.getString("addr"));
				return mem;
			}
		}, null);
	}	
	
}
