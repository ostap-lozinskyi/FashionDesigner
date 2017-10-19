package ua.model.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import ua.entity.Meal;
import ua.entity.Place;
import ua.validation.flag.OrderFlag;

public class OrderRequest {

	private Integer id;

	@NotNull(message = "This field cannot be blank", groups = { OrderFlag.class })
	private Place place;
	
	@NotNull(message = "This field cannot be blank", groups = { OrderFlag.class })
	private List<Meal> meals = new ArrayList<>();
	
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
