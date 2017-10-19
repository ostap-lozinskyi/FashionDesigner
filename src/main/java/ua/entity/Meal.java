package ua.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "meal", indexes=@Index(columnList = "name", unique=true))
public class Meal extends AbstractEntityName {

	private String photoUrl;

	private int version;

	private BigDecimal rate;
	
	private int votesAmount;
	
	private int votesCount;

	private String fullDescription;

	private String shortDescription;

	private BigDecimal price;

	private int weight;

	@ManyToOne(fetch = FetchType.LAZY)
	private Cuisine cuisine;

	@ManyToMany(mappedBy = "meals")
	private List<Order> orders = new ArrayList<>();

	@ManyToMany
	private List<Component> components = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Comment> comments;

	public Meal() {
	}

	public Meal(String name, String fullDescription, String shortDescription, BigDecimal price,
			List<Component> components, int weight, Cuisine cuisine) {
		super(name);
		this.fullDescription = fullDescription;
		this.shortDescription = shortDescription;
		this.price = price;
		this.weight = weight;
		this.cuisine = cuisine;
		this.components = components;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
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

	public int getVotesAmount() {
		return votesAmount;
	}

	public void setVotesAmount(int votesAmount) {
		this.votesAmount = votesAmount;
	}

	public int getVotesCount() {
		return votesCount;
	}

	public void setVotesCount(int votesCount) {
		this.votesCount = votesCount;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
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

	public Cuisine getCuisine() {
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
}
