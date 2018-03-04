package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "color", indexes=@Index(columnList = "name", unique=true))
public class Color extends AbstractEntityName {
	
	@ManyToMany(mappedBy="colors")
	private List<ClothingModel> clothingModels = new ArrayList<>();
	
	public Color() {
	}

	public Color(String name) {
		super(name);
	}

}
