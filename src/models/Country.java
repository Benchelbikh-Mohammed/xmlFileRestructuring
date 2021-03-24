package models;

public class Country {

	private String name;
	public Country() {
		super();
	}

	public Country(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "\nCountry [name=" + name + "]";
	}

}
