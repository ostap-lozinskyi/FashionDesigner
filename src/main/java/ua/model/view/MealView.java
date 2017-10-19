package ua.model.view;

import java.math.BigDecimal;
import java.util.List;

import ua.entity.Comment;

public class MealView {

	private Integer id;
	
	private String photoUrl;
	
	private int version;
	
	private BigDecimal rate;
	
	private String name;
	
	private String fullDescription;
	
	private BigDecimal price;
	
	private List<String> components;
	
	private int weight;
	
	private String cuisine;
	
	private List<Comment> comments;
	
	public MealView(Integer id, String photoUrl, int version, String name, String fullDescription, BigDecimal price,
			int weight, String cuisine, BigDecimal rate) {
		this.id = id;
		this.photoUrl = photoUrl;
		this.version = version;
		this.name = name;
		this.fullDescription = fullDescription;
		this.price = price;
		this.weight = weight;
		this.cuisine = cuisine;
		this.rate = rate;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<String> getComponents() {
		return components;
	}

	public void setComponents(List<String> components) {
		this.components = components;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "MealView [id=" + id + ", photoUrl=" + photoUrl + ", version=" + version + ", rate=" + rate + ", name="
				+ name + ", fullDescription=" + fullDescription + ", price=" + price + ", components=" + components
				+ ", weight=" + weight + ", cuisine=" + cuisine + "]";
	}
}