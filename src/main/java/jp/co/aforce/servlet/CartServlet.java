package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Product;
import jp.co.aforce.dao.ProductDAO;

@WebServlet("/views/CartServlet")
public class CartServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
	    HttpSession session = request.getSession();
	    List<Product> cart = (List<Product>) session.getAttribute("cart");

	    if (cart == null) {
	        cart = new ArrayList<>();
	    }

	    request.setAttribute("cart", cart);
	    request.getRequestDispatcher("/views/cart.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String productId = request.getParameter("productId");
		String size = request.getParameter("size");
		String quantityParam = request.getParameter("quantity");

		if (productId == null || size == null || productId.isEmpty() || size.isEmpty()) {
			request.setAttribute("errorMessage", "商品IDまたはサイズが指定されていません。");
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
			return;
		}

		int productIdInt = Integer.parseInt(productId);
		int quantity = Integer.parseInt(quantityParam); 

		ProductDAO productDAO = new ProductDAO();
		Product product = productDAO.getProductById(productIdInt);
		if (product == null) {
			request.setAttribute("errorMessage", "商品が見つかりません。");
			request.getRequestDispatcher("/views/error.jsp").forward(request, response);
			return;
		}

		product.setSize(size);  // サイズをセットする
		product.setQuantity(quantity);  // 数量をセット


		HttpSession session = request.getSession();
		List<Product> cart = (List<Product>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<>();
		}

		cart.add(product);
		session.setAttribute("cart", cart);

		response.sendRedirect(request.getContextPath() + "/UserMenuServlet");
	}
}
