package model;

public class Editora {

	private int publisher_id;
	private String name;
	private String url;
	
	public Editora(int pId, String pName, String  pUrl) {
		publisher_id = pId;
		name = pName;
		url = pUrl;
	}
	public int getPublisher_id() {
		return publisher_id;
	}
	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
