package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class NewUserConfirmServlet
 */
@WebServlet("/views/newUserConfirm")
public class NewUserConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// フォーム値取得
	    String id = request.getParameter("id");
	    String password = request.getParameter("password");
	    String lastName = request.getParameter("lastName");
	    String firstName = request.getParameter("firstName");
	    String address = request.getParameter("address");
	    String email = request.getParameter("email");
	    
	 // セッションに保存（←追加）
	    HttpSession session = request.getSession();
	    session.setAttribute("reg_id", id);
	    session.setAttribute("reg_password", password);
	    session.setAttribute("reg_last_name", lastName);
	    session.setAttribute("reg_first_name", firstName);
	    session.setAttribute("reg_address", address);
	    session.setAttribute("reg_email", email);
	    
	 // リクエストに属性としてセット
	    request.setAttribute("id", id);
	    request.setAttribute("password", password);
	    request.setAttribute("lastName", lastName);
	    request.setAttribute("firstName", firstName);
	    request.setAttribute("address", address);
	    request.setAttribute("email", email);
	    
	 // 確認画面へフォワード
	    request.getRequestDispatcher("/views/newUserConfirm.jsp").forward(request, response);
	}

}
