package jp.co.aforce.servlet.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.aforce.beans.Product;
import jp.co.aforce.dao.DAO;

/**
 * Servlet implementation class RegisterProduct
 */
@WebServlet("/RegisterProduct")
public class RegisterProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterProduct() {
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
		HttpSession session = request.getSession();

		// セッションから商品情報と在庫情報を取得
		Product product = (Product) session.getAttribute("newProduct");
		Map<Double, Integer> stockMap = (Map<Double, Integer>) session.getAttribute("stockMap");

		if (product == null || stockMap == null) {
			// セッション切れなど
			response.sendRedirect(request.getContextPath() + "/views/admin/adminAddProduct.jsp?error=sessionExpired");
			return;
		}
		
		try (Connection con = DAO.getConnection()) {

			// 商品情報を登録
			String sql = "INSERT INTO products (name, price, image, release_date) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, product.getName());
			ps.setInt(2, product.getPrice());
			ps.setString(3, product.getImage());
			ps.setDate(4, product.getRelease_date());
			ps.executeUpdate();

			// 登録された商品のIDを取得
			ResultSet rs = ps.getGeneratedKeys();
			int productId = 0;
			if (rs.next()) {
				productId = rs.getInt(1);
			}
			rs.close();
			ps.close();

			// 在庫情報を登録
			String stockSql = "INSERT INTO product_sizes (product_id, size, stock) VALUES (?, ?, ?)";
			PreparedStatement stockPs = con.prepareStatement(stockSql);
			for (Map.Entry<Double, Integer> entry : stockMap.entrySet()) {
				stockPs.setInt(1, productId);
				stockPs.setDouble(2, entry.getKey());
				stockPs.setInt(3, entry.getValue());
				stockPs.addBatch();
			}
			stockPs.executeBatch();
			stockPs.close();

			// セッション情報をクリア
			session.removeAttribute("newProduct");
			session.removeAttribute("stockMap");

			// 完了画面へ
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/registerComplete.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			// エラー画面へ
			response.sendRedirect(request.getContextPath() + "/views/admin/registerError.jsp");
		}
	}

}
