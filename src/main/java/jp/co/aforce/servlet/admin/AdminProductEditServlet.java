package jp.co.aforce.servlet.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.ProductSize;
import jp.co.aforce.beans.ProductWithStockSummary;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.dao.ProductSizeDAO;

/**
 * Servlet implementation class AdminProductEditServlet
 */
@WebServlet("/admin/AdminProductEditServlet")
public class AdminProductEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int productId = Integer.parseInt(request.getParameter("id"));

			ProductDAO dao = new ProductDAO();
			ProductWithStockSummary product = dao.getProductWithStockSummary(productId);
			
			ProductSizeDAO sizeDao = new ProductSizeDAO();
			List<ProductSize> sizes = sizeDao.getSizesByProductId(productId);
			request.setAttribute("sizes", sizes);

			if (product != null) {
				request.setAttribute("product", product);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/adminProductEdit.jsp");
				dispatcher.forward(request, response);
				
			} else {
				request.setAttribute("errorMessage", "商品が見つかりませんでした");
				request.getRequestDispatcher("/views/admin/error.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "商品の取得に失敗しました");
			request.getRequestDispatcher("/views/admin/error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
