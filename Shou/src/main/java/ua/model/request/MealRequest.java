package ua.model.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import ua.entity.Component;
import ua.entity.Cuisine;
import ua.validation.annotation.UniqueMeal;
import ua.validation.flag.MealFlag;

public class MealRequest {

	private Integer id;

	@UniqueMeal(message = "Such a meal already exists", groups = MealFlag.class)
	@NotBlank(message = "This field cannot be blank", groups = { MealFlag.class })
	@Pattern(regexp = "^[A-Z][A-Za-z0-9 ]+| *$", message = "The 'Name' should begin with a capital letter and have at least 2 letters", groups = {
			MealFlag.class })
	private String name;

	@NotBlank(message = "This field cannot be blank", groups = { MealFlag.class })
	private String fullDescription;

	@NotBlank(message = "This field cannot be blank", groups = { MealFlag.class })
	private String shortDescription;

	@NotBlank(message = "This field cannot be blank", groups = { MealFlag.class })
	@Pattern(regexp = "^([0-9]{1,18}\\.[0-9]{0,2})|([0-9]{1,18}\\,[0-9]{0,2})|([0-9]{1,18})| *$", message = "The 'Prise' should be a number", groups = {
			MealFlag.class })
	private String price;

	@NotBlank(message = "This field cannot be blank", groups = { MealFlag.class })
	@Pattern(regexp = "^[1-9][0-9]*| *$", message = "The Weight should be a number and can not begin with a zero symbol", groups = {
			MealFlag.class })
	private String weight;
	
	@NotNull(message = "This field cannot be blank", groups = { MealFlag.class })
	private Cuisine cuisine;

	@NotEmpty(message = "This field cannot be blank", groups = { MealFlag.class })
	private List<Component> components = new ArrayList<>();
	
	private String photoUrl;
	
	private int version;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
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
	
}
