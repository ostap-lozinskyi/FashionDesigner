package ua.model.view;

import java.util.ArrayList;
import java.util.List;

public class ClothingModelView {

	private Integer id;

	private String name;

	private String text;
	
	private String season;	
	
	private String typeOfClothes;
	
	private String sectionOfClothes;
	
	private List<String> colors = new ArrayList<>();
	
	private String photoUrl;	

	private int version;
	
	public ClothingModelView(Integer id, String name, String text, String season, String typeOfClothes, 
			String sectionOfClothes, String photoUrl, int version) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.season = season;
		this.photoUrl = photoUrl;
		this.version = version;
	}

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

	public String getTypeOfClothes() {
		return typeOfClothes;
	}

	public void setTypeOfClothes(String typeOfClothes) {
		this.typeOfClothes = typeOfClothes;
	}

	public String getSectionOfClothes() {
		return sectionOfClothes;
	}

	public void setSectionOfClothes(String sectionOfClothes) {
		this.sectionOfClothes = sectionOfClothes;
	}

	public List<String> getColors() {
		return colors;
	}

	public void setColors(List<String> colors) {
		this.colors = colors;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
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