package com.bridgelabz.addressbook.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bridgelabz.addressbook.dto.AddressBook;
import com.bridgelabz.addressbook.dto.AddressDictionary;
import com.bridgelabz.addressbook.dto.Person;
import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.service.IAddressBook;
import com.bridgelabz.addressbook.type.ActionType;

public class AddressBookService implements IAddressBook {

	private static final Logger LOG = LogManager.getLogger(AddressBookService.class);
	private Scanner sc;
	public static AddressDictionary addressDictionary = new AddressDictionary();
	private Map<String, List<Person>> cityAndPersonMap;
	private Map<String, List<Person>> stateAndPersonMap;

	public AddressBookService() {
		this.sc = new Scanner(System.in);
		this.cityAndPersonMap = new HashMap<String, List<Person>>();
		this.stateAndPersonMap = new HashMap<String, List<Person>>();
	}

	/**
	 * this method is used to initiate address book
	 * 
	 * @throws AddressBookException
	 */
	@Override
	public void populateAddressBook(String bookName) throws AddressBookException {
		boolean switcher = true;
		do {
			LOG.info("\n\tAddress Book Menu");
			LOG.info("\n\t\tEnter A to (A)dd Person ");
			LOG.info("\t\tEnter D to (D)elete Person");
			LOG.info("\t\tEnter M to (M)odify Person");
			LOG.info("\t\tEnter Q to Quit ");
			System.out.print("\n\tPlease enter your choice: ");
			char choice = sc.nextLine().toUpperCase().charAt(0);
			while ((choice != 'A') && (choice != 'D') && (choice != 'M') && (choice != 'Q')) {
				LOG.info("Invalid choice!  Please select (A)dd, (D)elete, (M)odify or (Q)uit: ");
				choice = sc.nextLine().toUpperCase().charAt(0);
			}
			switch (choice) {
			case 'A':
				inputUserDetails(ActionType.CREATE, bookName);
				break;
			case 'D':
				inputUserDetails(ActionType.DELETE, bookName);
				break;
			case 'M':
				inputUserDetails(ActionType.EDIT, bookName);
				break;
			case 'Q':
				switcher = false;
				break;
			default:
			}
		} while (switcher != false);
	}

	/**
	 * Perform create,edit or delete person based on actions given
	 * 
	 * @param actionType
	 * @return
	 * @throws AddressBookException
	 */
	private void inputUserDetails(ActionType actionType, String bookName) throws AddressBookException {
		try {
			AddressBook addressBook = addressDictionary.getAddressBookList().stream()
					.filter(book -> book.getName().equalsIgnoreCase(bookName)).collect(Collectors.toList()).stream()
					.findFirst().orElseThrow(NoSuchElementException::new);
			Map<String, Person> personMap = addressBook.getPersonMap();
			switch (actionType) {
			case CREATE:
				Person newPerson = new Person();
				LOG.info("\nTo add a person, follow the prompts.");
				LOG.info("\nEnter Firstname: ");
				newPerson.setFirstName(sc.nextLine());
				inputCommonFields(newPerson);
				if (personMap.containsKey(newPerson.getFirstName())) {
					String errorMessage = "Person with this name already exists. Please choose a different name";
					throw new AddressBookException(errorMessage);
				} else {
					personMap.put(newPerson.getFirstName(), newPerson);
					LOG.debug(addressBook.toString());
					LOG.debug("\nYou have successfully added a new person!");
				}
				break;
			case EDIT:
				LOG.info("\nTo edit a person, follow the prompts.");
				LOG.info("\nEnter the first name of the person to edit");
				String firstName = sc.nextLine();
				Person editedPerson = personMap.get(firstName);
				if (null != editedPerson && null != editedPerson.getFirstName()) {
					inputCommonFields(editedPerson);
					LOG.debug(addressBook.toString());
				}
				break;
			case DELETE:
				LOG.info("\nTo delete a person, follow the prompts.");
				LOG.info("\nEnter the first name of the person to be deleted");
				String name = sc.nextLine();
				personMap.remove(name);
				LOG.debug(addressBook.toString());
				break;
			}
			addressBook.setPersonMap(personMap);
		} catch (AddressBookException e) {
			LOG.error(e.getMessage());
			throw new AddressBookException(e.getMessage());
		} catch (Exception e) {
			throw new AddressBookException(e.getMessage());
		}
	}

	/**
	 * To find person by city.
	 */
	public Map<String, List<Person>> findPersonByCity(String city) {
		List<Person> personList = new ArrayList<Person>();
		for (AddressBook addressBook : addressDictionary.getAddressBookList()) {
			Map<String, Person> personMap = addressBook.getPersonMap();
			Iterator personMapIterator = personMap.entrySet().iterator();
			while (personMapIterator.hasNext()) {
				Map.Entry personMapEntry = (Map.Entry) personMapIterator.next();
				Person person = (Person) personMapEntry.getValue();
				if (person.getCity().equalsIgnoreCase(city)) {
					personList.add(person);
				}
			}
		}
		LOG.debug("PersonList in city " + city + " is " + personList.toString());
		cityAndPersonMap.put(city, personList);
		return cityAndPersonMap;
	}

	/**
	 * To find person by state.
	 */
	public Map<String, List<Person>> findPersonByState(String state) {
		List<Person> personList = new ArrayList<Person>();
		for (AddressBook addressBook : addressDictionary.getAddressBookList()) {
			Map<String, Person> personMap = addressBook.getPersonMap();
			Iterator personMapIterator = personMap.entrySet().iterator();
			while (personMapIterator.hasNext()) {
				Map.Entry personMapEntry = (Map.Entry) personMapIterator.next();
				Person person = (Person) personMapEntry.getValue();
				if (person.getState().equalsIgnoreCase(state)) {
					personList.add(person);
				}
			}
		}
		LOG.debug("PersonList in state " + state + " is " + personList.toString());
		stateAndPersonMap.put(state, personList);
		return stateAndPersonMap;
	}

	/**
	 * to get the count of person by city
	 */
	public int getPersonCountByCity(String city) {
		Map<String, List<Person>> cityAndPersonMap = findPersonByCity(city);
		LOG.debug("Count of PersonList in city " + city + " is " + cityAndPersonMap.get(city).size());
		return cityAndPersonMap.get(city).size();
	}

	/**
	 * to get the count of person by state
	 */
	public int getPersonCountByState(String state) {
		Map<String, List<Person>> stateAndPersonMap = findPersonByState(state);
		LOG.debug("Count of PersonList in state " + state + " is " + stateAndPersonMap.get(state).size());
		return stateAndPersonMap.get(state).size();
	}

	/**
	 * This method sorts the person name in alphabetical order
	 */
	public void sortByPersonName() {
		for (AddressBook addressBook : addressDictionary.getAddressBookList()) {
			Map<String, Person> personMap = addressBook.getPersonMap();
			LOG.debug("Sorting person by first name");
			personMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
		}
	}

	/**
	 * sort person by state,city or zip.
	 * 
	 * @param property
	 */
	public void sortPersonByAttribute(String attribute) {
		for (AddressBook addressBook : addressDictionary.getAddressBookList()) {
			Map<String, Person> personMap = addressBook.getPersonMap();
			LOG.debug("Sorting person by " + attribute);
			personMap.entrySet().stream().sorted(Map.Entry.comparingByValue(getComparator(attribute)))
					.forEach(System.out::println);
		}
	}

	public Comparator<? super Person> getComparator(String property) {
		Comparator<? super Person> cmp = null;
		switch (property) {
		case "city":
			cmp = new SortByCity();
			break;
		case "state":
			cmp = new SortByState();
			break;
		case "zip":
			cmp = new SortByZip();
			break;
		}
		return cmp;
	}

	/**
	 * common input fields for both create and edit
	 * 
	 * @param person
	 */
	private void inputCommonFields(Person person) {
		System.out.print("\nEnter Lastname: ");
		person.setLastName(sc.nextLine());
		System.out.print("Enter Address: ");
		person.setAddress(sc.nextLine());
		System.out.print("Enter City: ");
		person.setCity(sc.nextLine());
		System.out.print("Enter State: ");
		person.setState(sc.nextLine());
		System.out.print("Enter Zip: ");
		person.setZip(sc.nextLine());
		System.out.print("Enter Phone Number: ");
		person.setPhoneNumber(sc.nextLine());
	}

	@Override
	public void addAddressBook(String name) {
		Map<String, Person> personMap = new HashMap<String, Person>();
		AddressBook addressBook = new AddressBook(name, personMap);
		addressDictionary.getAddressBookList().add(addressBook);
	}

	Comparator<Person> compareByCity = (Person obj1, Person obj2) -> obj1.getCity().toLowerCase()
			.compareTo(obj2.getCity().toLowerCase());

	Comparator<Person> compareByState = (Person obj1, Person obj2) -> obj1.getState().toLowerCase()
			.compareTo(obj2.getState().toLowerCase());

	Comparator<Person> compareByZip = (Person obj1, Person obj2) -> obj1.getZip().toLowerCase()
			.compareTo(obj2.getZip().toLowerCase());
}

class SortByCity implements Comparator<Person> {
	public int compare(Person a, Person b) {
		return a.getCity().toLowerCase().compareTo(b.getCity().toLowerCase());
	}
}

class SortByState implements Comparator<Person> {
	public int compare(Person a, Person b) {
		return a.getState().toLowerCase().compareTo(b.getState().toLowerCase());
	}
}

class SortByZip implements Comparator<Person> {
	public int compare(Person a, Person b) {
		return a.getZip().toLowerCase().compareTo(b.getZip().toLowerCase());
	}
}
