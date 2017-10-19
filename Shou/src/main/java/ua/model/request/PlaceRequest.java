package ua.model.request;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import ua.validation.annotation.UniquePlace;
import ua.validation.flag.PlaceFlag;

public class PlaceRequest {

	private Integer id;
	
	@UniquePlace(message = "Such a meal already exists", groups = PlaceFlag.class)
	@NotBlank(message = "This field cannot be blank", groups = { PlaceFlag.class })
	@Pattern(regexp = "^[1-9][0-9]*| *$", message = "The Number should be a number and can not begin with a zero symbol", groups = {
			PlaceFlag.class })
	private String number;

	@NotBlank(message = "This field cannot be blank", groups = { PlaceFlag.class })
	@Pattern(regexp = "^[1-9][0-9]*| *$", message = "The Count Of People should be a number and can not begin with a zero symbol", groups = {
			PlaceFlag.class })
	private String countOfPeople;	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountOfPeople() {
		return countOfPeople;
	}

	public void setCountOfPeople(String countOfPeople) {
		this.countOfPeople = countOfPeople;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
