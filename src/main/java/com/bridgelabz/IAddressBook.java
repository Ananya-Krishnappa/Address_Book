package com.bridgelabz;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public interface IAddressBook {
	public void addAddressBook(String name) throws AddressBookException;

	public void populateAddressBook(String bookName) throws AddressBookException;

	public Map<String, List<Person>> findPersonByCity(String city);

	public Map<String, List<Person>> findPersonByState(String state);

	public int getPersonCountByState(String state);

	public int getPersonCountByCity(String city);

	public void sortByPersonName();

	public void sortPersonByAttribute(String attribute);

	/**
	 * Reading the Json file and convert to java object using Jackson ObjectMapper
	 * readValue method
	 * @throws AddressBookException 
	 */
	public void readJson() throws AddressBookException;

	/**
	 * Converting the Java objects to JSON file writeValueAsString
	 * @throws AddressBookException 
	 */
	public void writeToJson() throws AddressBookException;

}
