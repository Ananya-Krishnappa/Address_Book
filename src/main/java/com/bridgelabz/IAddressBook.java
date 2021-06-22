package com.bridgelabz;

import java.util.List;

public interface IAddressBook {
	public void addAddressBook(String name) throws AddressBookException;

	public void populateAddressBook(String bookName) throws AddressBookException;

	public List<Person> findPersonByCity(String string);

}
