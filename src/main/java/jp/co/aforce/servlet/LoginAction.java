package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Login;
import jp.co.aforce.beans.Product;
import jp.co.aforce.dao.LoginDAO;
import jp.co.aforce.dao.ProductDAO;

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
				
				//バリデーション（空チェック）
				if (id == null || id.isEmpty() || password == null || password.isEmpty()) {
				    request.setAttribute("errorMsg", "会員番号とパスワードを入力してください。");
				    request.getRequestDispatcher("/views/login-in.jsp").forward(request, response);
				    return;
				}

				LoginDAO logindao = new LoginDAO();
				//	            Login user = dao.loginCheck(id, password);

				//データベースから全ユーザーをもらう
				List<Login> users = logindao.getALLlogin();
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

					// セッションからログインユーザーを取得
					Login loginUser = (Login) request.getSession().getAttribute("user");

					if (loginUser.getAdmin() == 1) {
						// 管理者なら adminHome.jsp にリダイレクト
						response.sendRedirect(request.getContextPath() + "/views/admin/adminHome.jsp");
					} else {
						//DAOのインスタンスを作る
						ProductDAO productdao = new ProductDAO();

						//商品一覧を取得
						List<Product> productList = productdao.getAllProducts();
						HttpSession session = request.getSession();
						//取得した商品リストをリクエストスコープにセット
						session.setAttribute("products", productList);

						//user-menu.jspにフォワード
						request.getRequestDispatcher("/views/user-menu.jsp").forward(request, response);
					}

				} else {
					// 失敗時、エラーと入力されたIDを渡して、login-in.jspにフォワード
				    request.setAttribute("errorMsg", "会員番号またはパスワードが正しくありません");
				    request.setAttribute("enteredId", id);  // 入力されたIDを保存
				    request.getRequestDispatcher("/views/login-in.jsp").forward(request, response);
				}
				return;

			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/views/login-error.jsp");
				return;
			}

		} else if ("backTop".equals(action)) {
			try {
				ProductDAO productdao = new ProductDAO();
				List<Product> productList = productdao.getAllProducts();

				request.setAttribute("products", productList);
				request.getRequestDispatcher("/views/user-menu.jsp").forward(request, response);
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
