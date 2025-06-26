package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Product;
import jp.co.aforce.beans.ProductSize;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.dao.ProductSizeDAO;

@WebServlet("/UserMenuServlet")
public class UserMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserMenuServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sort = request.getParameter("sort"); // 並び替えパラメータ取得

		ProductDAO productDao = new ProductDAO();
		List<Product> products;

		// 並び替え条件によって取得方法を変更
		if ("price_asc".equals(sort)) {
			products = productDao.getProductsOrderBy("price", "ASC");
		} else if ("price_desc".equals(sort)) {
			products = productDao.getProductsOrderBy("price", "DESC");
		} else if ("release_new".equals(sort)) {
			products = productDao.getProductsOrderBy("release_date", "DESC");
		} else if ("release_old".equals(sort)) {
			products = productDao.getProductsOrderBy("release_date", "ASC");
		} else {
			products = productDao.getAllProducts(); 
		}

		// サイズ情報を取得
		ProductSizeDAO sizeDao = new ProductSizeDAO();
		Map<String, List<ProductSize>> productSizesMap = new HashMap<>();

		for (Product product : products) {
			String productId = String.valueOf(product.getId());
			List<ProductSize> sizes = sizeDao.getSizesByProductId(product.getId());
			productSizesMap.put(productId, sizes);

//		
//			for (ProductSize ps : sizes) {
//				System.out.println("  サイズ: " + ps.getSize() + ", 在庫: " + ps.getStock());
//			}
	}

		// JSPへ渡す
		request.setAttribute("products", products);
		request.setAttribute("productSizesMap", productSizesMap);
		request.setAttribute("sort", sort);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user-menu.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
