package ua.model.request;

import org.hibernate.validator.constraints.NotBlank;

import ua.validation.flag.CollectionFlag;

public class CollectionRequest {

	private Integer id;

	@NotBlank(message = "This field cannot be blank", groups = { CollectionFlag.class })
	private String name;

	@NotBlank(message = "This field cannot be blank", groups = { CollectionFlag.class })
	private String text;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
