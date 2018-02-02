package ua.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "collection", indexes=@Index(columnList = "title"))
public class Collection extends AbstractEntity {

	private String title;

	@Column(columnDefinition = "TEXT")
	private String text;
	
	private String photoUrl;
	
	private String date;

	private int version;

	public Collection() {
	}

	public Collection(String title, String text, String photoUrl, String date, int version) {
		this.title = title;
		this.text = text;
		this.photoUrl = photoUrl;
		this.date = date;
		this.version = version;
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
