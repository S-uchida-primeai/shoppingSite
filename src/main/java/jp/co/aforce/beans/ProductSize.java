package jp.co.aforce.beans;

import java.io.Serializable;

public class ProductSize implements Serializable {
	private static final long serialVersionUID = 1L;

	private int productId;
	private double size;
	private int stock;

	public ProductSize() {
	}

	public ProductSize(int productId, double size, int stock) {
		this.productId = productId;
		this.size = size;
		this.stock = stock;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}
