package model;

public class Item {
	private int id;
	private String Descricao;

	public Item(int id, String description) {
		this.id = id;
		this.Descricao = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return Descricao;
	}

	public String toString() {
		return Descricao;
	}
}
