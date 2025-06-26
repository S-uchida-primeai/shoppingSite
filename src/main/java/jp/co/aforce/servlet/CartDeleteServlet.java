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

@WebServlet("/CartDeleteServlet")
public class CartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String productId = request.getParameter("productId");
		String size = request.getParameter("size");

		HttpSession session = request.getSession();
		List<Product> cart = (List<Product>) session.getAttribute("cart");

		if (cart != null) {
			cart.removeIf(p -> String.valueOf(p.getId()).equals(productId) && p.getSize().equals(size));
		}

		response.sendRedirect(request.getContextPath() + "/views/CartServlet");
	}
}
