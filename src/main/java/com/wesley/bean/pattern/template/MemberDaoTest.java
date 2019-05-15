package com.wesley.bean.pattern.template;

import java.util.List;

public class MemberDaoTest {
	public static void main(String[] args) {
		MemberDao mDao=new MemberDao(null);
		List<?> result = mDao.selectAll();
		System.out.println(result);
	}

}
