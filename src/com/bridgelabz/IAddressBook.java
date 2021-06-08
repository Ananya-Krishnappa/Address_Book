package com.bridgelabz;

import java.util.List;

public interface IAddressBook {
	public void addAddressBook(String name, List<Person> personList);

	public void populateAddressBook(String bookName);

}
