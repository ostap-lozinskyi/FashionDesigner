package ua.model.view;

public class PlaceView {
	
	private Integer id;
	
	private int countOfPeople;

	private int number;
	
	private boolean isFree;
	
	public PlaceView(Integer id, int countOfPeople, int number, boolean isFree) {
		this.id = id;
		this.countOfPeople = countOfPeople;
		this.number = number;
		this.isFree = isFree;
	}

	public String getPrint() {
		return "Table_"+number+ "_"+ "CountOfPeople_"+countOfPeople;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCountOfPeople() {
		return countOfPeople;
	}

	public void setCountOfPeople(int countOfPeople) {
		this.countOfPeople = countOfPeople;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

}
