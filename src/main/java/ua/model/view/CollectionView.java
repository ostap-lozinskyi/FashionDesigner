package ua.model.view;

public class CollectionView {

	private Integer id;
	
	private String name;
	
	private String text;
	
	private String date;
	
	private String photoUrl;
	
	private int version;
	
	public CollectionView(Integer id, String name, String text, String date, String photoUrl, int version) {
		this.id = id;
		this.name = name;
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