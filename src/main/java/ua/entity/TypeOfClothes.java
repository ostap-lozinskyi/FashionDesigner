package ua.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "typeOfClothes", indexes = @Index(columnList = "name", unique = true))
public class TypeOfClothes extends AbstractEntityName {

	public TypeOfClothes() {
	}

	public TypeOfClothes(String name) {
		super(name);
	}	

}
