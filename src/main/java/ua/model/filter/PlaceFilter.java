package ua.model.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PlaceFilter {
	
	private static final Pattern INT_PATTERN = Pattern.compile("^[0-9]{1,10}$");

	private String number = "";

	private List<String> countOfPeople = new ArrayList<>();
	
	private String isFree = "";

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		if(INT_PATTERN.matcher(number).matches())
		this.number = number;
	}

	public List<String> getCountOfPeople() {
		return countOfPeople;
	}

	public void setCountOfPeople(List<String> countOfPeople) {
		this.countOfPeople = countOfPeople;
	}

	public String getIsFree() {
		return isFree;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}

}
