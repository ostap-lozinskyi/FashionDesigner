package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
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
	
	@ElementCollection(targetClass=String.class)
	private List<String> photoUrls = new ArrayList<>();	

	@ElementCollection(targetClass=Integer.class)
	private List<Integer> versions = new ArrayList<>();

	public ClothingModel() {
	}

	public ClothingModel(String name, String text, Season season,
			TypeOfClothes typeOfClothes, SectionOfClothes sectionOfClothes, 
			List<Color> colors, List<String> photoUrls, List<Integer> versions) {
		super(name);
		this.text = text;
		this.season = season;	
		this.typeOfClothes = typeOfClothes;
		this.sectionOfClothes = sectionOfClothes;
		this.colors = colors;
		this.photoUrls = photoUrls;		
		this.versions = versions;
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

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public List<Integer> getVersions() {
		return versions;
	}

	public void setVersions(List<Integer> versions) {
		this.versions = versions;
	}

}
