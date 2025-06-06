package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Login;
import jp.co.aforce.dao.LoginDAO;

/**
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/views/UserEditServlet")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserEditServlet() {
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
		request.setCharacterEncoding("UTF-8");

		try {

			// セッションからログインユーザー情報を取得
			HttpSession session = request.getSession(false);
			Login user = (Login) session.getAttribute("user");

			if (user == null) {
				// セッションが切れていたらログイン画面に戻す
				response.sendRedirect(request.getContextPath() + "/views/login-in.jsp");
				return;
			}
			//入力した値を取得
			String newLastName = request.getParameter("lastName");
			String newFirstName = request.getParameter("firstName");
			String newEmail = request.getParameter("email");

			// DAOの呼び出し
			LoginDAO dao = new LoginDAO();
			int result = dao.updateUser(user.getId(), newLastName, newFirstName, newEmail);

			if (result == 1) {
				// 更新成功したらセッションのユーザー情報も更新しておく
				user.setLastName(newLastName);
				user.setFirstName(newFirstName);
				user.setEmail(newEmail);
				session.setAttribute("user", user);

				// 編集完了ページかメニュー画面へリダイレクト
				response.sendRedirect(request.getContextPath() + "/views/user-menu.jsp");
			} else {
				// 更新失敗時（何かエラーあれば編集画面に戻すなど）
				request.getRequestDispatcher("userEdit.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/views/error.jsp");
		}

	}
}
