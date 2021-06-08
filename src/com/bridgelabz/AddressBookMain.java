package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBookMain {
	private static Scanner sc = new Scanner(System.in);
	private List<Person> personList;

	public AddressBookMain() {
		this.personList = new ArrayList<Person>();
	}

	public static void main(String[] args) {
		AddressBookMain addressBookMain = new AddressBookMain();
		addressBookMain.initiateAddressBook();
	}

	/**
	 * this method is used to initiate address book
	 */
	private void initiateAddressBook() {
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
				inputUserDetails("create");
				break;
			case 'D':
				inputUserDetails("delete");
				break;
			case 'M':
				inputUserDetails("edit");
				break;
			case 'Q':
				switcher = false;
				sc.close();
				System.exit(0);
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
	 */
	private Person inputUserDetails(String action) {
		if (action.equalsIgnoreCase("create")) {
			Person newPerson = new Person();
			System.out.println("\nTo add a person, follow the prompts.");
			System.out.print("\nEnter Firstname: ");
			newPerson.setFirstName(sc.nextLine());
			inputCommonFields(newPerson);
			personList.add(newPerson);
			System.out.println(personList.toString());
			System.out.println("\nYou have successfully added a new person!");
			return newPerson;
		} else if (action.equalsIgnoreCase("edit")) {
			System.out.println("\nTo edit a person, follow the prompts.");
			System.out.println("\nEnter the first name of the person to edit");
			String firstName = sc.nextLine();
			Person editedPerson = personList.stream()
					.filter(person -> person.getFirstName().equalsIgnoreCase(firstName)).findFirst().orElse(null);
			if (null != editedPerson && null != editedPerson.getFirstName()) {
				inputCommonFields(editedPerson);
				personList = personList.stream()
						.map(p -> p.getFirstName().equalsIgnoreCase(editedPerson.getFirstName()) ? editedPerson : p)
						.collect(Collectors.toList());
				System.out.println(personList.toString());
			}
		} else if (action.equalsIgnoreCase("delete")) {
			System.out.println("\nTo delete a person, follow the prompts.");
			System.out.println("\nEnter the first name of the person to be deleted");
			String firstName = sc.nextLine();
			personList = personList.stream().filter(p -> !(p.getFirstName().equalsIgnoreCase(firstName)))
					.collect(Collectors.toList());
			System.out.println(personList.toString());
		}
		return null;
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
}
