package ua.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "collection", indexes=@Index(columnList = "name"))
public class Collection extends AbstractEntityName {

	@ManyToOne(fetch=FetchType.LAZY)
	private TypeOfCollection typeOfCollection;

	@Column(columnDefinition = "TEXT")
	private String text;
	
	private String photoUrl;
	
	private String date;

	private int version;

	public Collection() {
	}

	public Collection(String name, TypeOfCollection typeOfCollection, String text, String photoUrl, String date, int version) {
		super(name);
		this.typeOfCollection = typeOfCollection;
		this.text = text;
		this.photoUrl = photoUrl;
		this.date = date;
		this.version = version;
	}

	public TypeOfCollection getTypeOfCollection() {
		return typeOfCollection;
	}

	public void setTypeOfCollection(TypeOfCollection typeOfCollection) {
		this.typeOfCollection = typeOfCollection;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
