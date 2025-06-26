package jp.co.aforce.servlet.admin;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.User;
import jp.co.aforce.dao.UserDAO;

/**
 * Servlet implementation class AdminUserEditSuccessServlet
 */
@WebServlet("/admin/AdminUserEditSuccessServlet")
public class AdminUserEditSuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserEditSuccessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			// パラメータ取得
			String memberId = request.getParameter("member_id");
			String lastName = request.getParameter("last_name");
			String firstName = request.getParameter("first_name");
			String address = request.getParameter("address");
			String mailAddress = request.getParameter("mail_address");
			int admin = Integer.parseInt(request.getParameter("admin"));

			// Beanにセット
			User user = new User();
			user.setMemberId(memberId);
			user.setLastName(lastName);
			user.setFirstName(firstName);
			user.setAddress(address);
			user.setMailAddress(mailAddress);
			user.setAdmin(admin);

			// 更新処理
			UserDAO dao = new UserDAO();
			dao.updateUser(user);  // ← このメソッドをDAOに作成しておくこと

			// 完了画面へ遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/adminUserEditSuccess.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/admin/AdminUserList");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
