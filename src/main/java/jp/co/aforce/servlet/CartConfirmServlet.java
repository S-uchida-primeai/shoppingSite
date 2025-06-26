package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Product;

/**
 * Servlet implementation class CartConfirmServlet
 */
@WebServlet("/CartConfirmServlet")
public class CartConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartConfirmServlet() {
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
		HttpSession session = request.getSession();
		List<Product> cart = (List<Product>) session.getAttribute("cart");

		if (cart != null) {
			for (int i = 0; i < cart.size(); i++) {
				String size = request.getParameter("size_" + i);
				String quantityStr = request.getParameter("quantity_" + i);
				if (size != null && !size.isEmpty()) {
					cart.get(i).setSize(size);
				}
				if (quantityStr != null && !quantityStr.isEmpty()) {
					try {
						int quantity = Integer.parseInt(quantityStr);
						cart.get(i).setQuantity(quantity);
					} catch (NumberFormatException e) {
						// 数字じゃなかった場合は 1 にするなどの対処
						cart.get(i).setQuantity(1);
					}
				}
			}
		}

		// セッションに反映（任意）
		session.setAttribute("cart", cart);

		// JSPに cart を渡す
		request.setAttribute("cart", cart);

		request.getRequestDispatcher("/views/cartConfirm.jsp").forward(request, response);
	}

}
