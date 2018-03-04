package ua.entity;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.List;

import org.hibernate.validator.constraints.NotBlank;

import ua.validation.annotation.UniqueSeason;
import ua.validation.annotation.UniqueSectionOfClothes;
import ua.validation.annotation.UniqueTypeOfClothes;
import ua.validation.flag.ClothingModelFlag;
import ua.validation.flag.SeasonFlag;
import ua.validation.flag.SectionOfClothesFlag;
import ua.validation.flag.TypeOfClothesFlag;

@MappedSuperclass
public abstract class AbstractEntityName extends AbstractEntity {

	@UniqueTypeOfClothes(message = "This type already exists", groups = TypeOfClothesFlag.class)
	@UniqueSectionOfClothes(message = "This section already exists", groups = SectionOfClothesFlag.class)
	@UniqueSeason(message = "This season already exists", groups = SeasonFlag.class)
	@NotBlank(message = "This field cannot be blank", groups = {SeasonFlag.class, TypeOfClothesFlag.class, ClothingModelFlag.class,
			SectionOfClothesFlag.class})
	@List({@Pattern(regexp = "^[A-Za-z0-9 _-А-Яа-я]+| *$", message = "The 'Name' should have at least 1 letter",	
		groups = {SeasonFlag.class, TypeOfClothesFlag.class, ClothingModelFlag.class, SectionOfClothesFlag.class})})
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
