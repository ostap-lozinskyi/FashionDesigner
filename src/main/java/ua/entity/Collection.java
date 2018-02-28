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
	
	public Collection() {
	}

	public Collection(String name, TypeOfCollection typeOfCollection, String text) {
		super(name);
		this.typeOfCollection = typeOfCollection;
		this.text = text;
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

}
