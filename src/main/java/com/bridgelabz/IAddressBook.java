package com.bridgelabz;

import java.util.List;
import java.util.Map;

public interface IAddressBook {
	public void addAddressBook(String name) throws AddressBookException;

	public void populateAddressBook(String bookName) throws AddressBookException;

	public Map<String, List<Person>> findPersonByCity(String city);

	public Map<String, List<Person>> findPersonByState(String state);

}
