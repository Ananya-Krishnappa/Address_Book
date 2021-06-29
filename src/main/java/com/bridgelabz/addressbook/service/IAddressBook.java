package com.bridgelabz.addressbook.service;

import java.util.List;
import java.util.Map;

import com.bridgelabz.addressbook.dto.Person;
import com.bridgelabz.addressbook.exception.AddressBookException;

public interface IAddressBook {
	public void addAddressBook(String name) throws AddressBookException;

	public void populateAddressBook(String bookName) throws AddressBookException;

	public Map<String, List<Person>> findPersonByCity(String city);

	public Map<String, List<Person>> findPersonByState(String state);

	public int getPersonCountByState(String state);

	public int getPersonCountByCity(String city);

	public void sortByPersonName();

	public void sortPersonByAttribute(String attribute);
}
