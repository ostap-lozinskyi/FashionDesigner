package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ingredient", indexes = @Index(columnList = "name", unique = true))
public class Ingredient extends AbstractEntityName {

	@OneToMany(mappedBy = "ingredient")
	private List<Component> components = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY)
	private List<Comment> comments;

	public Ingredient() {
	}

	public Ingredient(String name) {
		super(name);
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
