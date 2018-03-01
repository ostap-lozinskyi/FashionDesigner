package ua.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clothingModel", indexes=@Index(columnList = "name"))
public class ClothingModel extends AbstractEntityName {
	
	private String date;
	
	private String text;
	
	private String furniture;

	@ManyToOne(fetch=FetchType.LAZY)
	private Season season;	
	
	private String photoUrl;	

	private int version;

	public ClothingModel() {
	}

	public ClothingModel(String name, String date, String text, String furniture, Season season,
			String photoUrl, int version) {
		super(name);
		this.date = date;
		this.text = text;
		this.furniture = furniture;
		this.season = season;		
		this.photoUrl = photoUrl;		
		this.version = version;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFurniture() {
		return furniture;
	}

	public void setFurniture(String furniture) {
		this.furniture = furniture;
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

}
