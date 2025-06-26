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
import jp.co.aforce.dao.OrderDAO;

@WebServlet("/OrderSuccess")
public class OrderSuccess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderSuccess() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("▶ OrderSuccess に入りました");

		HttpSession session = request.getSession();
		List<Product> sessionCart = (List<Product>) session.getAttribute("cart");

		if (sessionCart == null || sessionCart.isEmpty()) {
			System.out.println("▶ sessionCart が null または空です");
			response.sendRedirect(request.getContextPath() + "/views/cart.jsp");
			return;
		}

		try {
			int count = Integer.parseInt(request.getParameter("count"));
			System.out.println("▶ 受信した商品数: " + count);

			List<Product> cart = new ArrayList<>();

			for (int i = 0; i < count; i++) {
				String idStr = request.getParameter("product_id_" + i);
				String size = request.getParameter("size_" + i);
				String quantityStr = request.getParameter("quantity_" + i);

				System.out.println("▶ [" + i + "] ID=" + idStr + ", size=" + size + ", quantity=" + quantityStr);

				if (idStr == null || size == null || quantityStr == null) {
					System.out.println("▶ [" + i + "] パラメータが足りません。スキップします。");
					continue;
				}

				Product p = new Product();
				p.setId(Integer.parseInt(idStr));
				p.setSize(size);
				p.setQuantity(Integer.parseInt(quantityStr));

				for (Product s : sessionCart) {
					if (s.getId() == p.getId() && s.getSize().equals(p.getSize())) {
						p.setName(s.getName());
						p.setPrice(s.getPrice());
						break;
					}
				}

				cart.add(p);
			}

			//System.out.println("▶ 商品リストの再構築完了。商品数: " + cart.size());

			OrderDAO dao = new OrderDAO();

			for (Product p : cart) {
				//System.out.println("▶ 在庫更新前: 商品ID=" + p.getId() + ", サイズ=" + p.getSize());
				boolean updated = dao.updateStock(p.getId(), p.getSize());
				//System.out.println("▶ 在庫更新結果: " + updated);

				if (!updated) {
					//System.out.println("▶ 在庫不足でエラー: " + p.getName() + " / サイズ: " + p.getSize());
					request.setAttribute("error", "商品「" + p.getName() + "」の在庫が不足しています。");
					request.getRequestDispatcher("/views/error.jsp").forward(request, response);
					return;
				}

				dao.insertOrder(p);
				//System.out.println("▶ 購入履歴登録完了: 商品ID=" + p.getId());
			}

			request.setAttribute("cart", cart);      // 完了画面に渡す
			session.removeAttribute("cart");         // その後セッションから削除

			//System.out.println("▶ カート削除＆完了ページに遷移します");
			request.getRequestDispatcher("/views/orderSuccess.jsp").forward(request, response);

		} catch (Exception e) {
			//System.out.println("▶ 例外発生: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("error", "購入処理中にエラーが発生しました。" + e.getMessage());
			request.getRequestDispatcher("/views/orderError.jsp").forward(request, response);
		}
	}
}
