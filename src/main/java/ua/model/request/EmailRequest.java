package ua.model.request;

import org.hibernate.validator.constraints.NotBlank;

import ua.validation.flag.EmailFlag;

public class EmailRequest {

	@NotBlank(message = "This field cannot be blank", groups = {EmailFlag.class})
	private String tel;

	@NotBlank(message = "This field cannot be blank", groups = {EmailFlag.class})
	private String text;

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
