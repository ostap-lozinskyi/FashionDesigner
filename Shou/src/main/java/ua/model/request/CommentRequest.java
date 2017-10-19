package ua.model.request;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import ua.validation.flag.CommentFlag;

public class CommentRequest {

	private Integer id;
	
	@NotBlank(message = "This field cannot be blank", groups = { CommentFlag.class })
	private String text;

	private Date date;

	private String user;
	
	private Boolean isPositive;
	
	private Integer rate;

	public String getText() {
		return text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Boolean getIsPositive() {
		return isPositive;
	}

	public void setIsPositive(Boolean isPositive) {
		this.isPositive = isPositive;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

}
