package com.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dap.IAccount;
import com.shop.impl.AccountImpl;
import com.shop.pojo.Account;

public class AccountServlet extends HttpServlet {

	private IAccount accountImpl = new AccountImpl();
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public AccountServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String status = request.getParameter("status");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
        
		if (status.equals("login")) {
			Account account = new Account();
			account.setUsername(username);
			account.setPassword(password);
			//查询用户
			account = accountImpl.queryAccount(account);
			if (account == null) { //登录失败
				request.setAttribute("error", "登录失败");
				//内部跳转
				request.getRequestDispatcher("/alogin.jsp").forward(request, response);
			} else {
				request.getSession().setAttribute("account", account);
				//外部跳转
				response.sendRedirect(request.getContextPath() + "/admin/index.jsp");
			}
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
