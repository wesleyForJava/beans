package com.wesley.bean.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@WebServlet(urlPatterns="/test2")第二种方式实现servlet
public class TestServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置返回类型为json
		resp.setContentType("application/json");
		//设置返回字符集
		resp.setCharacterEncoding("utf-8");
		//输出对象
		PrintWriter writer=resp.getWriter();
		//输出error消息
		writer.write("执行TestServlet内doGet方法成功!");
        writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

}
