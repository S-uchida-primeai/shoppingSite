package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Login;
import jp.co.aforce.dao.LoginDAO;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/views/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		String action2 = request.getParameter("action2");
		
		
		if ("login".equals(action)) {

			try {
				//IDとパスワードを受け取る
				String id = request.getParameter("id");
				String password = request.getParameter("password");

				LoginDAO dao = new LoginDAO();
				//	            Login user = dao.loginCheck(id, password);

				//データベースから全ユーザーをもらう
				List<Login> users = dao.getALLlogin();
				Boolean matchFound = false;
				// 入力されたIDとパスワードが一致するか確認
				for (Login user : users) {

					if (user.getId().equals(id) && user.getPassword().equals(password)) {
						matchFound = true;
						request.getSession().setAttribute("user", user);
						break; // 一致したらループを抜ける
					}
				}

				// ログイン成功・失敗で分岐
				if (matchFound) {
					response.sendRedirect("user-menu.jsp");
				} else {
					response.sendRedirect("login-error.jsp");
				}
				return;

			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/views/login-error.jsp");
				return;
			}
			
		} else if ("register".equals(action2)) {

				response.sendRedirect(request.getContextPath() + "/views/newUser.jsp");
			
			
	
		} else {

			response.sendRedirect(request.getContextPath() + "/views/login-in.jsp");
		}
		
		
		
	}
}
