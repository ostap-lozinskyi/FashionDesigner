package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cuisine", indexes=@Index(columnList = "name", unique=true))
public class Cuisine extends AbstractEntityName{
	
	@OneToMany(mappedBy="cuisine")
	private List<Meal> meals = new ArrayList<>();
	
	public Cuisine(String name) {
		super(name);
	}

	public Cuisine() {
	}

}
