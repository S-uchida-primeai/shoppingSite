package jp.co.aforce.servlet.admin;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Product;
import jp.co.aforce.dao.ProductDAO;

/**
 * Servlet implementation class InsertProduct
 */
@WebServlet("/views/InsertProduct")
public class InsertProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertProduct() {
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
		try {
			// パラメータ取得
			String name = request.getParameter("name");
			int price = Integer.parseInt(request.getParameter("price"));
			String image = request.getParameter("image");
			String releaseDateStr = request.getParameter("release_date");

			// 日付の変換
			Date releaseDate = Date.valueOf(releaseDateStr); // yyyy-MM-dd 形式前提

			// Productオブジェクトに詰める
			Product product = new Product();
			product.setName(name);
			product.setPrice(price);
			product.setImage(image);
			product.setRelease_date(releaseDate);

			// DAOで登録
			ProductDAO dao = new ProductDAO();
			int insertedId = dao.insertProduct(product); 
			
			System.out.println("登録されたID: " + insertedId); // ← デバッグログ

			// 登録後、在庫入力画面へ product_id を渡して遷移
			request.setAttribute("product_id", insertedId);
			request.getRequestDispatcher("/views/admin/addProductStock.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/views/admin/adminAddProduct.jsp?error=true");
		}
	}

}
