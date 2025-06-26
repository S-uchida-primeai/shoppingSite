package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.Product;
import jp.co.aforce.beans.ProductWithStockSummary;

public class ProductDAO extends DAO {

	public List<Product> searchByKeyword(String keyword) throws Exception {
		Connection con = getConnection();
		List<Product> list = new ArrayList<>();

		String sql = "SELECT * FROM products WHERE name LIKE ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + keyword + "%");
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			Product p = new Product();
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setPrice(rs.getInt("price"));
			p.setImage(rs.getString("image"));
			list.add(p);
		}

		con.close();
		return list;
	}

	public List<Product> getAllProducts() {
		List<Product> productList = new ArrayList<>();

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			String sql = "SELECT * FROM products";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				String image = rs.getString("image");
				java.sql.Date releaseDate = rs.getDate("release_date");

				Product product = new Product(id, name, price, image, releaseDate);

				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productList;
	}
	
	public int insertProduct(Product product) throws Exception {
		Connection con = getConnection();

		String sql = "INSERT INTO products (name, price, image, release_date) VALUES (?, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		st.setString(1, product.getName());
		st.setInt(2, product.getPrice());
		st.setString(3, product.getImage());
		st.setDate(4, product.getRelease_date());

		st.executeUpdate();

		ResultSet rs = st.getGeneratedKeys();
		int productId = -1;
		if (rs.next()) {
			productId = rs.getInt(1);
		}

		st.close();
		con.close();

		return productId;
	}
	
	public List<ProductWithStockSummary> getAllProductsWithStockSummary() throws Exception {
		List<ProductWithStockSummary> list = new ArrayList<>();
		Connection con = getConnection();

		String sql = """
			SELECT 
				p.id,
				p.name,
				p.price,
				p.image,
				p.release_date,
				GROUP_CONCAT(CONCAT(ps.size, '(', ps.stock, ')') ORDER BY ps.size SEPARATOR ', ') AS stock_summary
			FROM products p
			LEFT JOIN product_sizes ps ON p.id = ps.product_id
			GROUP BY p.id, p.name, p.price, p.image, p.release_date
			ORDER BY p.id
		""";

		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			ProductWithStockSummary product = new ProductWithStockSummary();
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getInt("price"));
			product.setImage(rs.getString("image"));
			product.setReleaseDate(rs.getString("release_date"));
			product.setStockSummary(rs.getString("stock_summary"));

			list.add(product);
		}

		rs.close();
		st.close();
		con.close();

		return list;
	}
	
	public ProductWithStockSummary getProductWithStockSummary(int productId) throws Exception {
		Connection con = getConnection();

		String sql = """
			SELECT 
				p.id,
				p.name,
				p.price,
				p.image,
				p.release_date,
				GROUP_CONCAT(CONCAT(ps.size, '(', ps.stock, ')') ORDER BY ps.size SEPARATOR ', ') AS stock_summary
			FROM products p
			LEFT JOIN product_sizes ps ON p.id = ps.product_id
			WHERE p.id = ?
			GROUP BY p.id, p.name, p.price, p.image, p.release_date
		""";

		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, productId);
		ResultSet rs = st.executeQuery();

		ProductWithStockSummary product = null;
		if (rs.next()) {
			product = new ProductWithStockSummary();
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getInt("price"));
			product.setImage(rs.getString("image"));
			product.setReleaseDate(rs.getString("release_date"));
			product.setStockSummary(rs.getString("stock_summary"));
		}

		rs.close();
		st.close();
		con.close();

		return product;
	}
	
	public void updateProduct(int id, String name, int price, String image, String releaseDate) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(
			"UPDATE products SET name=?, price=?, image=?, release_date=? WHERE id=?"
		);
		st.setString(1, name);
		st.setInt(2, price);
		st.setString(3, image);
		st.setString(4, releaseDate);
		st.setInt(5, id);
		st.executeUpdate();
		st.close();
		con.close();
	}
	
	public void deleteProduct(String productId) throws Exception {
	    Connection con = getConnection();
	    try {
	        PreparedStatement ps1 = con.prepareStatement("DELETE FROM product_sizes WHERE product_id = ?");
	        ps1.setString(1, productId);
	        ps1.executeUpdate();

	        PreparedStatement ps2 = con.prepareStatement("DELETE FROM products WHERE id = ?");
	        ps2.setString(1, productId);
	        ps2.executeUpdate();
	    } finally {
	        con.close();
	    }
	}
	
	//並び替え
	public List<Product> getProductsOrderBy(String column, String direction) {
	    List<Product> products = new ArrayList<>();
	    try (Connection con = getConnection()) {
	        String sql = "SELECT * FROM products ORDER BY " + column + " " + direction;
	        PreparedStatement st = con.prepareStatement(sql);
	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            Product product = new Product();
	            product.setId(rs.getInt("id"));
	            product.setName(rs.getString("name"));
	            product.setPrice(rs.getInt("price"));
	            product.setRelease_date(rs.getDate("release_date"));
	            product.setImage(rs.getString("image"));
	            products.add(product);
	        }
	        rs.close();
	        st.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return products;
	}
	
	public Product getProductById(int productId) {
		try (Connection con = getConnection()) {
			String sql = "SELECT * FROM products WHERE id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, productId);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setImage(rs.getString("image"));
				product.setRelease_date(rs.getDate("release_date"));
				return product;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}






}
