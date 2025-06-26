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
import jp.co.aforce.beans.ProductWithStockSummary;

/**
 * Servlet implementation class AdminProductEditConfirmServlet
 */
@WebServlet("/admin/AdminProductEditConfirmServlet")
public class AdminProductEditConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductEditConfirmServlet() {
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
		
		// 商品基本情報の取得
		int productId = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		String image = request.getParameter("image");
		String releaseDate = request.getParameter("release_date");

		// サイズと在庫の取得
		String[] sizeStrs = request.getParameterValues("size");
		String[] stockStrs = request.getParameterValues("stock");

		List<ProductSize> sizeList = new ArrayList<>();
		for (int i = 0; i < sizeStrs.length; i++) {
			double size = Double.parseDouble(sizeStrs[i]);
			int stock = Integer.parseInt(stockStrs[i]);
			sizeList.add(new ProductSize(productId, size, stock));
		}

		// 確認用データをリクエストスコープへ
		ProductWithStockSummary product = new ProductWithStockSummary();
		product.setId(productId);
		product.setName(name);
		product.setPrice(price);
		product.setImage(image);
		product.setReleaseDate(releaseDate);

		request.setAttribute("product", product);
		request.setAttribute("sizes", sizeList);

		// 確認画面に転送
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/confirmProductEdit.jsp");
		dispatcher.forward(request, response);		
	}

}
