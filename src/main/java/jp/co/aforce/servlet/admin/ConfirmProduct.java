package jp.co.aforce.servlet.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Product;

@WebServlet("/ConfirmProduct")
public class ConfirmProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConfirmProduct() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();  // セッションを最初に1回だけ取得

		// フォームから商品情報を受け取る
		String name = request.getParameter("name");
		String priceStr = request.getParameter("price");
		String image = request.getParameter("image");
		String releaseDateStr = request.getParameter("release_date");

		if (name != null && priceStr != null && releaseDateStr != null) {
			int price = Integer.parseInt(priceStr);
			java.sql.Date releaseDate = java.sql.Date.valueOf(releaseDateStr);

			Product product = new Product();
			product.setName(name);
			product.setPrice(price);
			product.setImage(image);
			product.setRelease_date(releaseDate);

			session.setAttribute("newProduct", product);
		}

		// セッションから商品情報を取得して null チェック
		Object productObj = session.getAttribute("newProduct");
		if (productObj == null) {
			response.sendRedirect(request.getContextPath() + "/views/admin/adminAddProduct.jsp?error=true");
			return;
		}

		// 在庫数を受け取って保存
		double[] sizes = {25.0, 25.5, 26.0, 26.5, 27.0, 27.5, 28.0};
		Map<Double, Integer> stockMap = new HashMap<>();

		for (double size : sizes) {
			String param = request.getParameter("stock_" + size);
			if (param != null && !param.isEmpty()) {
				int stock = Integer.parseInt(param);
				stockMap.put(size, stock);
			}
		}

		session.setAttribute("stockMap", stockMap);

		// 確認画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/confirmProduct.jsp");
		dispatcher.forward(request, response);
	}
}
