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
 * Servlet implementation class CartUpdateServlet
 */
@WebServlet("/CartUpdateServlet")
public class CartUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartUpdateServlet() {
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

		String productIdStr = request.getParameter("productId");
		String selectedSize = request.getParameter("size");

		if (productIdStr == null || selectedSize == null || cart == null) {
			response.sendRedirect(request.getContextPath() + "/views/cart.jsp");
			return;
		}

		int productId = Integer.parseInt(productIdStr);

		// カート内の対象商品にサイズをセット
		for (Product product : cart) {
			if (product.getId() == productId) {
				product.setSize(selectedSize);
				break;
			}
		}

		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath() + "/views/cart.jsp");
	}

}
