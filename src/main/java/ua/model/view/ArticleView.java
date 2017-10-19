package ua.model.view;

import java.util.Date;
import java.util.List;

import ua.entity.Comment;

public class ArticleView {

	private Integer id;
	
	private String title;
	
	private String text;
	
	private String date;
	
	private String photoUrl;
	
	private int version;
	
	private List<Comment> comments;
	
	public ArticleView(Integer id, String title, String text, String date, String photoUrl, int version) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.date = date;
		this.photoUrl = photoUrl;
		this.version = version;
	}

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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}