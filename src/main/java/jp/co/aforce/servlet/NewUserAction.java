package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Login;
import jp.co.aforce.dao.LoginDAO;

/**
 * Servlet implementation class NewUserAction
 */
@WebServlet("/views/newuseraction")
public class NewUserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewUserAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			// getParameterでフォームから値を取得
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String lastName = request.getParameter("lastName");
			String firstName = request.getParameter("firstName");
			String address = request.getParameter("address");
			String email = request.getParameter("email");

			// ユーザー情報をbeanにまとめる
			Login user = new Login(id, password, lastName, firstName, address, email);

			// DAOを使ってDBに登録
			LoginDAO dao = new LoginDAO();
			int result = dao.insertUser(user);

			if (result == 1) {
				// 登録成功
				response.sendRedirect("userSuccess.jsp");
			} else {
				// 登録失敗（ID重複など）
				response.sendRedirect("newUserError.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("newUserError.jsp");
		}
	}

}
