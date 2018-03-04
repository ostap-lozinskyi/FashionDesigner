package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clothingModel", indexes=@Index(columnList = "name"))
public class ClothingModel extends AbstractEntityName {
	
	private String text;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Season season;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private TypeOfClothes typeOfClothes;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private SectionOfClothes sectionOfClothes;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Color> colors = new ArrayList<>();
	
	private String photoUrl;	

	private int version;

	public ClothingModel() {
	}

	public ClothingModel(String name, String text, Season season,
			TypeOfClothes typeOfClothes, SectionOfClothes sectionOfClothes, 
			List<Color> colors, String photoUrl, int version) {
		super(name);
		this.text = text;
		this.season = season;	
		this.typeOfClothes = typeOfClothes;
		this.sectionOfClothes = sectionOfClothes;
		this.colors = colors;
		this.photoUrl = photoUrl;		
		this.version = version;
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

}
