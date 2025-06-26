package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.ProductSize;

public class ProductSizeDAO extends DAO {

    // 商品IDに対応するサイズ・在庫のリストを取得
    public List<ProductSize> getSizesByProductId(int productId) {
        List<ProductSize> sizeList = new ArrayList<>();

        try (
            Connection con = getConnection();
            PreparedStatement st = con.prepareStatement(
                "SELECT size, stock FROM product_sizes WHERE product_id = ?"
            )
        ) {
            st.setInt(1, productId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                double size = rs.getDouble("size");
                int stock = rs.getInt("stock");

                ProductSize ps = new ProductSize(productId, size, stock);
                sizeList.add(ps);
            }

        } catch (Exception e) {
            e.printStackTrace();  // 本番ではロギング推奨
        }

        return sizeList;
    }
    
    public void updateStock(ProductSize ps) throws Exception {
    	Connection con = getConnection();
    	PreparedStatement st = con.prepareStatement(
    		"UPDATE product_sizes SET stock=? WHERE product_id=? AND size=?"
    	);
    	st.setInt(1, ps.getStock());
    	st.setInt(2, ps.getProductId());
    	st.setDouble(3, ps.getSize());
    	st.executeUpdate();
    	st.close();
    	con.close();
    }

}
