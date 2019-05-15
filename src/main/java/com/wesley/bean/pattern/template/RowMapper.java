package com.wesley.bean.pattern.template;

import java.sql.ResultSet;

public interface RowMapper<T> {

	T rowMap(ResultSet result,int rowNum) throws Exception;
}
