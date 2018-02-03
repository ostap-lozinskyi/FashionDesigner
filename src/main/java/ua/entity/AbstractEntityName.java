package ua.entity;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.List;

import org.hibernate.validator.constraints.NotBlank;

import ua.validation.annotation.UniqueTypeOfCollection;
import ua.validation.flag.TypeOfCollectionFlag;

@MappedSuperclass
public abstract class AbstractEntityName extends AbstractEntity {

	@UniqueTypeOfCollection(message = "Such an ingredient already exists", groups = TypeOfCollectionFlag.class)
	@NotBlank(message = "This field cannot be blank", groups = { Collection.class, TypeOfCollectionFlag.class})
	@List({@Pattern(regexp = "^[A-Za-z0-9]+| *$", message = "The 'Name' should have at least 1 letter",	
		groups = {Collection.class, TypeOfCollectionFlag.class})})
	private String name;

	public AbstractEntityName(String name) {
		this.name = name;
	}

	public AbstractEntityName() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
