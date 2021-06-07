package com.bridgelabz;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

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

			Person newPerson = new Person();
			ArrayList<Person> personList = new ArrayList<Person>();

			switch (choice) {
			case 'A':
				System.out.println("\nTo add a person, follow the prompts.");

				System.out.print("\nEnter Fullname: ");
				newPerson.setFirstName(sc.nextLine());

				System.out.print("\nEnter Fullname: ");
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
				System.out.println(personList.get(personList.size() - 1).toString());
				System.out.println("\nYou have successfully added a new person!");

				break;

			case 'D':

				break;
			case 'M':

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
}
