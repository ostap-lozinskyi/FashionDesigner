package ua.model.request;

import org.hibernate.validator.constraints.NotBlank;

import ua.entity.Collection;
import ua.validation.flag.CollectionFlag;

public class ClothingModelRequest {

	private Integer id;

	@NotBlank(message = "This field cannot be blank", groups = { CollectionFlag.class })
	private String name;

	private String date;
	
	private String text;
	
	private String furniture;

	private Collection collection;	
	
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

	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
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
