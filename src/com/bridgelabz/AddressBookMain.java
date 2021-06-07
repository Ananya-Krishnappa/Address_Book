package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBookMain {
	private static Scanner sc = new Scanner(System.in);
	private static List<Person> personList = new ArrayList<Person>();

	public static void main(String[] args) {
		initiateAddressBook();
	}

	/**
	 * this method is used to initiate address book
	 */
	private static void initiateAddressBook() {
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
	private static Person inputUserDetails(String action) {
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
			/*
			 * personList.stream().filter(person ->
			 * person.getFirstName().equalsIgnoreCase(firstName)).findFirst().orElse(null);
			 */
			List<Person> filteredList = new ArrayList<Person>();
			for (int i = 0; i < personList.size(); i++) {
				if (personList.get(i).getFirstName().equalsIgnoreCase(firstName)) {
					filteredList.add(personList.get(i));
					break;
				}
			}
			if (null != filteredList && filteredList.size() >= 1) {
				Person person = filteredList.get(0);
				inputCommonFields(person);

				/*
				 * personList.stream().map(p ->
				 * p.getFirstName().equalsIgnoreCase(person.getFirstName()) ? person : p);
				 */
				for (int j = 0; j < personList.size(); j++) {
					if (personList.get(j).getFirstName().equalsIgnoreCase(person.getFirstName())) {
						personList.set(j, person);
					}
				}

				System.out.println(personList.toString());
			}
		} else if (action.equalsIgnoreCase("delete")) {
			System.out.println("\nTo delete a person, follow the prompts.");
			System.out.println("\nEnter the first name of the person to be deleted");
			String firstName = sc.nextLine();
			/*
			 * List<Person> filteredCollection = personList.stream() .filter(p ->
			 * !(p.getFirstName().equalsIgnoreCase(firstName))).collect(Collectors.toList())
			 * ;
			 */
			for (int k = 0; k < personList.size(); k++) {
				if (personList.get(k).getFirstName().equalsIgnoreCase(firstName)) {
					personList.remove(k);
				}
			}
			System.out.println(personList.toString());
		}
		return null;
	}

	/**
	 * common input fields for both create and edit
	 * 
	 * @param person
	 */
	private static void inputCommonFields(Person person) {
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
