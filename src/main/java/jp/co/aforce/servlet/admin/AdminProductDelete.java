package jp.co.aforce.servlet.admin;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.aforce.dao.ProductDAO;

@WebServlet("/admin/AdminProductDelete")
public class AdminProductDelete extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 商品ID取得
            String productId = request.getParameter("product_id");

            // DAOで削除実行
            ProductDAO dao = new ProductDAO();
            dao.deleteProduct(productId);

            // 削除完了画面にフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/productDeleteSuccess.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // エラーページにフォワード（任意）
            request.setAttribute("errorMessage", "商品の削除中にエラーが発生しました。");
            request.getRequestDispatcher("/views/admin/deleteError.jsp").forward(request, response);
        }
    }
}
