package com.bridgelabz;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookMain {
	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Person> personList = new ArrayList<Person>();

	public static void main(String[] args) {

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

	private static Person inputUserDetails(String action) {
		if (action.equalsIgnoreCase("create")) {
			Person newPerson = new Person();
			System.out.println("\nTo add a person, follow the prompts.");

			System.out.print("\nEnter Firstname: ");
			newPerson.setFirstName(sc.nextLine());

			System.out.print("\nEnter Lastname: ");
			newPerson.setLastName(sc.nextLine());

			System.out.print("Enter Address: ");
			newPerson.setAddress(sc.nextLine());

			System.out.print("Enter City: ");
			newPerson.setCity(sc.nextLine());

			System.out.print("Enter State: ");
			newPerson.setState(sc.nextLine());

			System.out.print("Enter Zip: ");
			newPerson.setZip(sc.nextLine());

			System.out.print("Enter Phone Number: ");
			newPerson.setPhoneNumber(sc.nextLine());

			personList.add(newPerson);
			System.out.println(personList.toString());
			System.out.println("\nYou have successfully added a new person!");
			return newPerson;
		} else {
			System.out.println("\nTo edit a person, follow the prompts.");
			System.out.println("\nEnter the first name of the person to edit");
			String firstName = sc.nextLine();
			/*
			 * personList.stream().filter(person ->
			 * person.getFirstName().equalsIgnoreCase(firstName)).findFirst() .orElse(null);
			 */
			ArrayList<Person> filteredList = new ArrayList<Person>();
			for (int i = 0; i < personList.size(); i++) {
				if (personList.get(i).getFirstName().equalsIgnoreCase(firstName)) {
					filteredList.add(personList.get(i));
					break;
				}
			}
			if (null != filteredList && filteredList.size() >= 1) {
				Person person = filteredList.get(0);
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
		}
		return null;
	}
}
