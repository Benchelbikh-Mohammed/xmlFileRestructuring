package models;

import java.util.ArrayList;
import java.util.List;

public class Religion {
	private String name;
	private List<Country> countries;
	public Religion() {
		super();
		countries=new ArrayList<Country>();
	}

	public Religion(String name) {
		super();
		this.name = name;
		countries=new ArrayList<Country>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	@Override
	public String toString() {
		String str="------------------\nReligion [name=" + name + "] , countries : " + countries.size() ;
		for(Country c : countries)
			str+=c;
		return str;
	}

	

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.name.length();
	}

	
	
}
