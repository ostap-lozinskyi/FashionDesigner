package ua.model.request;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import ua.validation.flag.ArticleFlag;

public class ArticleRequest {

	private Integer id;

	@NotBlank(message = "This field cannot be blank", groups = { ArticleFlag.class })
	private String title;

	@NotBlank(message = "This field cannot be blank", groups = { ArticleFlag.class })
	private String text;
	
	private String date;
	
	private String photoUrl;
	
	private int version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
