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
 * Servlet implementation class UserDeletServlet
 */
@WebServlet("/views/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDeleteServlet() {
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

		//コンフォームの情報を取得
		String confirm = request.getParameter("confirm");

		//IDはセッションから取得
		Login user = (Login) request.getSession().getAttribute("user");

		String memberId = user.getId();

		if ("yes".equals(confirm)) {
			try {
				LoginDAO dao = new LoginDAO();
				int result = dao.deleteUser(memberId);

				if (result == 1) {
					response.sendRedirect("userDeleteSuccess.jsp");
				} else {
					//request.setAttribute("error", "削除に失敗しました");
					request.getRequestDispatcher("userDelete.jsp").forward(request, response);
				}

			} catch (Exception e) {
				e.printStackTrace();
				//request.setAttribute("error", "エラーが発生しました");
				request.getRequestDispatcher("userDelete.jsp").forward(request, response);

			}

		} else if ("no".equals(confirm)) {

			response.sendRedirect("user-menu.jsp");
		}
	}

}
