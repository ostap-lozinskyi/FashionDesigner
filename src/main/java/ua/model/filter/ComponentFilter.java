package ua.model.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ComponentFilter {

	private static final Pattern DECIMAL_PATTERN = Pattern.compile("^([0-9]{1,18}\\.[0-9]{0,2})|([0-9]{1,18}\\,[0-9]{0,2})|([0-9]{1,18})$");
	
	private String minAmount = "";
	
	private String maxAmount = "";
	
	private List<String> msName = new ArrayList<>();
	
	private List<String> ingredientName = new ArrayList<>();

	public String getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(String minAmount) {
		if(DECIMAL_PATTERN.matcher(minAmount).matches())
		this.minAmount = minAmount;
	}

	public String getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(String maxAmount) {
		if(DECIMAL_PATTERN.matcher(maxAmount).matches())
		this.maxAmount = maxAmount;
	}

	public List<String> getMsName() {
		return msName;
	}

	public void setMsName(List<String> msName) {
		this.msName = msName;
	}

	public List<String> getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(List<String> ingredientName) {
		this.ingredientName = ingredientName;
	}

}
