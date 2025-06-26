package jp.co.aforce.servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.ProductSize;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.dao.ProductSizeDAO;

/**
 * Servlet implementation class AdminProductUpdateServlet
 */
@WebServlet("/admin/AdminProductUpdateServlet")
public class AdminProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductUpdateServlet() {
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
		request.setCharacterEncoding("UTF-8");

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			int price = Integer.parseInt(request.getParameter("price"));
			String image = request.getParameter("image");
			String releaseDate = request.getParameter("release_date");

			String[] sizeStrs = request.getParameterValues("size");
			String[] stockStrs = request.getParameterValues("stock");

			List<ProductSize> sizes = new ArrayList<>();
			for (int i = 0; i < sizeStrs.length; i++) {
				double size = Double.parseDouble(sizeStrs[i]);
				int stock = Integer.parseInt(stockStrs[i]);
				sizes.add(new ProductSize(id, size, stock));
			}

			// 商品情報更新
			ProductDAO productDao = new ProductDAO();
			productDao.updateProduct(id, name, price, image, releaseDate);

			// サイズごとの在庫更新
			ProductSizeDAO sizeDao = new ProductSizeDAO();
			for (ProductSize ps : sizes) {
				sizeDao.updateStock(ps);
			}

			// 完了ページへ
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/adminProductUpdateSuccess.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "更新に失敗しました");
			request.getRequestDispatcher("/views/admin/error.jsp").forward(request, response);
		}
	}

}
