package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ms", indexes=@Index(columnList = "name", unique=true))
public class Ms extends AbstractEntityName{	

	public Ms() {
	}
	
	public Ms(String name) {
		super(name);
	}

	@OneToMany(mappedBy="ms")
	private List<Component> components = new ArrayList<>();

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}
}
