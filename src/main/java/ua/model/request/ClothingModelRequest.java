package ua.model.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import ua.entity.Color;
import ua.entity.Season;
import ua.entity.SectionOfClothes;
import ua.entity.TypeOfClothes;
import ua.validation.annotation.UniqueClothingModel;
import ua.validation.flag.ClothingModelFlag;

public class ClothingModelRequest {

	private Integer id;

	@UniqueClothingModel(message = "This clothing model already exists", groups = ClothingModelFlag.class)
	@NotBlank(message = "This field cannot be blank", groups = {ClothingModelFlag.class})
	@Pattern(regexp = "^[A-Za-z0-9 _-А-Яа-яіІїЇєЄ]+| *$", message = "The 'Name' should have at least 1 letter",	
	groups = ClothingModelFlag.class)
	private String name;

	private String text;
	
	private Season season;	
	
	private TypeOfClothes typeOfClothes;
	
	private SectionOfClothes sectionOfClothes;
	
	private List<Color> colors = new ArrayList<>();
	
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public TypeOfClothes getTypeOfClothes() {
		return typeOfClothes;
	}

	public void setTypeOfClothes(TypeOfClothes typeOfClothes) {
		this.typeOfClothes = typeOfClothes;
	}

	public SectionOfClothes getSectionOfClothes() {
		return sectionOfClothes;
	}

	public void setSectionOfClothes(SectionOfClothes sectionOfClothes) {
		this.sectionOfClothes = sectionOfClothes;
	}

	public List<Color> getColors() {
		return colors;
	}

	public void setColors(List<Color> colors) {
		this.colors = colors;
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
