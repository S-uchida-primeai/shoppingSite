package jp.co.aforce.beans;

public class ProductWithStockSummary {
	
	private int id;
	private String name;
	private int price;
	private String image;
	private String releaseDate;
	private String stockSummary;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getStockSummary() {
		return stockSummary;
	}
	public void setStockSummary(String stockSummary) {
		this.stockSummary = stockSummary;
	}

	
	
}
