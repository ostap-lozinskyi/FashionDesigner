package ua.model.view;

import java.math.BigDecimal;

public class MealIndexView {

	private Integer id;
	
	private String photoUrl;
	
	private int version;
	
	private BigDecimal rate;
	
	private BigDecimal price;
	
	private int weight;
	
	private String name;
	
	private String shortDescription;
	
	public MealIndexView(Integer id, String photoUrl, Integer version, BigDecimal rate, BigDecimal price, Integer weight,
			String name, String shortDescription) {
		super();
		this.id = id;
		this.photoUrl = photoUrl;
		this.version = version;
		this.rate = rate;
		this.price = price;
		this.weight = weight;
		this.name = name;
		this.shortDescription = shortDescription;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
}
