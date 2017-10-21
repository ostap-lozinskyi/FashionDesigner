package ua.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "article", indexes=@Index(columnList = "title"))
public class Article extends AbstractEntity {

	private String title;

	@Column(columnDefinition = "TEXT")
	private String text;
	
	private String photoUrl;
	
	private String date;

	private int version;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Comment> comments;

	public Article() {
	}

	public Article(String title, String text, String photoUrl, String date, int version, List<Comment> comments) {
		this.title = title;
		this.text = text;
		this.photoUrl = photoUrl;
		this.date = date;
		this.version = version;
		this.comments = comments;
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
