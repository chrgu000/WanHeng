package com.jxc.cn;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class initToken extends HttpServlet {
	private static final Logger log = Logger.getLogger(initToken.class);// ��־�ļ�

	/**
	 * Constructor of the object.
	 */
	public initToken() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@SuppressWarnings("static-access")
	public void init() throws ServletException {
		token t = new token();
		t.main(null);
		// System.out.println("������ִ���˸÷���");
		log.info("������ִ���˸÷���");
	}
}
