package ua.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "typeOfCollection", indexes = @Index(columnList = "name", unique = true))
public class TypeOfCollection extends AbstractEntityName {

	public TypeOfCollection() {
	}

	public TypeOfCollection(String name) {
		super(name);
	}	

}
