package ua.model.view;

public class ClothingModelView {

	private Integer id;

	private String name;

	private String date;
	
	private String text;
	
	private String furniture;

	private String collection;	
	
	private String photoUrl;	

	private int version;
	
	public ClothingModelView(Integer id, String name, String date, String text, String furniture, String collection, 
			String photoUrl, int version) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.text = text;
		this.furniture = furniture;
		this.collection = collection;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFurniture() {
		return furniture;
	}

	public void setFurniture(String furniture) {
		this.furniture = furniture;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
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

}