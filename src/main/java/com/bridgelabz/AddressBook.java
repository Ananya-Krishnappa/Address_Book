package com.bridgelabz;

import java.util.Map;

public class AddressBook {
	private String name;
	private Map<String, Person> personMap;

	public AddressBook(String name, Map<String, Person> personMap) {
		this.name = name;
		this.personMap = personMap;
	}

	public AddressBook() {
	}

	@Override
	public String toString() {
		return "AddressBook [name=" + name + ", personMap=" + personMap + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Person> getPersonMap() {
		return personMap;
	}

	public void setPersonMap(Map<String, Person> personMap) {
		this.personMap = personMap;
	}

}
