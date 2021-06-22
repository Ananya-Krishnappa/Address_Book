package com.bridgelabz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressBookService implements IAddressBook {

	private static final Logger LOG = LogManager.getLogger(AddressBookMain.class);
	private Scanner sc;
	private Map<String, AddressBook> addressBookMap;
	private Map<String, List<Person>> cityAndPersonMap;
	private Map<String, List<Person>> stateAndPersonMap;

	public AddressBookService() {
		this.sc = new Scanner(System.in);
		/* this.addressBookList = new ArrayList<AddressBook>(); */
		this.addressBookMap = new HashMap<String, AddressBook>();
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
			System.out.println("\n\tAddress Book Menu");
			System.out.println("\n\t\tEnter A to (A)dd Person ");
			System.out.println("\t\tEnter D to (D)elete Person");
			System.out.println("\t\tEnter M to (M)odify Person");
			System.out.println("\t\tEnter Q to Quit ");
			System.out.print("\n\tPlease enter your choice: ");
			char choice = sc.nextLine().toUpperCase().charAt(0);
			while ((choice != 'A') && (choice != 'D') && (choice != 'M') && (choice != 'Q')) {
				System.out.println("Invalid choice!  Please select (A)dd, (D)elete, (M)odify or (Q)uit: ");
				choice = sc.nextLine().toUpperCase().charAt(0);
			}
			switch (choice) {
			case 'A':
				inputUserDetails("create", bookName);
				break;
			case 'D':
				inputUserDetails("delete", bookName);
				break;
			case 'M':
				inputUserDetails("edit", bookName);
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
	 * @param action
	 * @return
	 * @throws AddressBookException
	 */
	private void inputUserDetails(String action, String bookName) throws AddressBookException {
		try {
			Map<String, Person> personMap = addressBookMap.get(bookName).getPersonMap();
			if (action.equalsIgnoreCase("create")) {
				Person newPerson = new Person();
				System.out.println("\nTo add a person, follow the prompts.");
				System.out.print("\nEnter Firstname: ");
				newPerson.setFirstName(sc.nextLine());
				inputCommonFields(newPerson);
				if (personMap.containsKey(newPerson.getFirstName())) {
					String errorMessage = "Person with this name already exists. Please choose a different name";
					throw new AddressBookException(errorMessage);
				} else {
					personMap.put(newPerson.getFirstName(), newPerson);
					LOG.debug(addressBookMap.get(bookName).toString());
					LOG.debug("\nYou have successfully added a new person!");
				}
			} else if (action.equalsIgnoreCase("edit")) {
				System.out.println("\nTo edit a person, follow the prompts.");
				System.out.println("\nEnter the first name of the person to edit");
				String firstName = sc.nextLine();
				Person editedPerson = personMap.get(firstName);
				if (null != editedPerson && null != editedPerson.getFirstName()) {
					inputCommonFields(editedPerson);
					LOG.debug(addressBookMap.get(bookName).toString());
				}
			} else if (action.equalsIgnoreCase("delete")) {
				System.out.println("\nTo delete a person, follow the prompts.");
				System.out.println("\nEnter the first name of the person to be deleted");
				String firstName = sc.nextLine();
				personMap.remove(firstName);
				LOG.debug(addressBookMap.get(bookName).toString());
			}
			addressBookMap.get(bookName).setPersonMap(personMap);
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
		Iterator addressBookIterator = addressBookMap.entrySet().iterator();
		while (addressBookIterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) addressBookIterator.next();
			AddressBook addressbook = (AddressBook) mapElement.getValue();
			Map<String, Person> personMap = addressbook.getPersonMap();
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
		Iterator addressBookIterator = addressBookMap.entrySet().iterator();
		while (addressBookIterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) addressBookIterator.next();
			AddressBook addressbook = (AddressBook) mapElement.getValue();
			Map<String, Person> personMap = addressbook.getPersonMap();
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
		/* addressBookList.add(addressBook); */
		addressBookMap.put(name, addressBook);
	}

}
