package jp.co.aforce.beans;

import java.io.Serializable;
import java.sql.Date;

public class Product implements Serializable {
	private int id;
	private String name;
	private int price;
	private String image;
	private Date release_date;
	private String size;
	private int quantity;

	public Product() {}

	public Product(int id, String name, int price, String image, Date release_date) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.release_date = release_date;
	}

	// ゲッターとセッター
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public int getPrice() { return price; }
	public void setPrice(int price) { this.price = price; }

	public String getImage() { return image; }
	public void setImage(String image) { this.image = image; }

	public Date getRelease_date() { return release_date; }
	public void setRelease_date(Date release_date) { this.release_date = release_date; }

	public String getSize() { return size; }
	public void setSize(String size) { this.size = size; }

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
