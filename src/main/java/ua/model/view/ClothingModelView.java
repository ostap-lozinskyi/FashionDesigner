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
	
	private List<String> photoUrls = new ArrayList<>();	

	private int version;
	
	public ClothingModelView(Integer id, String name, String text, String season, String typeOfClothes, 
			String sectionOfClothes, int version) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.season = season;
		this.typeOfClothes = typeOfClothes;
		this.sectionOfClothes = sectionOfClothes;
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

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}