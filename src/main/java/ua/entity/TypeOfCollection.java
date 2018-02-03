package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "typeOfCollection", indexes = @Index(columnList = "name", unique = true))
public class TypeOfCollection extends AbstractEntityName {

	@OneToMany(mappedBy = "typeOfCollection")
	private List<Collection> collections = new ArrayList<>();

	public TypeOfCollection() {
	}

	public TypeOfCollection(String name) {
		super(name);
	}	

	public List<Collection> getComponents() {
		return collections;
	}

	public void setComponents(List<Collection> collections) {
		this.collections = collections;
	}	

}
