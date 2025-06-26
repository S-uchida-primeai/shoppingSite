package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import jp.co.aforce.beans.Product;

public class OrderDAO extends DAO {

	// 在庫を1つ減らす（成功すれば true、失敗すれば false）
	public boolean updateStock(int productId, String size) throws Exception {
	    Connection con = getConnection();

	    try {
	        // 在庫を確認
	        String selectSql = "SELECT stock FROM product_sizes WHERE product_id = ? AND size = ?";
	        PreparedStatement selectStmt = con.prepareStatement(selectSql);
	        selectStmt.setInt(1, productId);
	        selectStmt.setString(2, size);
	        ResultSet rs = selectStmt.executeQuery();

	        if (!rs.next()) {
	            rs.close();
	            selectStmt.close();
	            con.close();
	            return false; // データなし
	        }

	        int stock = rs.getInt("stock");
	        rs.close();
	        selectStmt.close();

	        if (stock <= 0) {
	            con.close();
	            return false; // 在庫なし
	        }

	        // 在庫を1つ減らす
	        String updateSql = "UPDATE product_sizes SET stock = stock - 1 WHERE product_id = ? AND size = ?";
	        PreparedStatement updateStmt = con.prepareStatement(updateSql);
	        updateStmt.setInt(1, productId);
	        updateStmt.setString(2, size);
	        int rowsUpdated = updateStmt.executeUpdate();
	        updateStmt.close();

	        con.close();

	        return rowsUpdated > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        con.close();
	        throw e;
	    }
	}

	// 購入履歴を登録する
	public void insertOrder(Product p) throws Exception {
		//ログ
		//System.out.println("insertOrder呼び出し: 商品ID=" + p.getId() + ", サイズ=" + p.getSize());
		
		Connection con = getConnection();

		try {
			String insertSql = "INSERT INTO orders (product_id, name, price, size, purchase_date) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(insertSql);
			stmt.setInt(1, p.getId());
			stmt.setString(2, p.getName());
			stmt.setInt(3, p.getPrice());
			
			//ログ
			 if (p.getSize() == null) {
		            throw new Exception("商品サイズがnullです！");
		        }
			
			stmt.setString(4, p.getSize());
			stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			stmt.executeUpdate();

		} finally {
			con.close();
		}
	}
}
